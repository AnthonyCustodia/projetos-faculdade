import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import Alerta from "../components/Alerta";
import api, { mensagemDeErro } from "../services/api";

// Mesmo formulario para cadastrar (/produtos/novo) e editar (/produtos/:id/editar).
// Nao ha campo de estoque: ele so muda por movimentacoes.
const ProdutoForm = () => {
  const { id } = useParams();
  const editando = Boolean(id);
  const navigate = useNavigate();

  const [form, setForm] = useState({ codigoBarras: "", nome: "", descricao: "" });
  const [produto, setProduto] = useState(null);
  const [carregando, setCarregando] = useState(editando);
  const [erro, setErro] = useState("");
  const [salvando, setSalvando] = useState(false);

  useEffect(() => {
    if (!editando) return;
    let ativo = true;
    api
      .get(`/produtos/${id}`)
      .then(({ data }) => {
        if (!ativo) return;
        setProduto(data);
        setForm({
          codigoBarras: data.codigoBarras,
          nome: data.nome,
          descricao: data.descricao || "",
        });
      })
      .catch((e) => ativo && setErro(mensagemDeErro(e)))
      .finally(() => ativo && setCarregando(false));
    return () => {
      ativo = false;
    };
  }, [id, editando]);

  function alterar(event) {
    setForm({ ...form, [event.target.name]: event.target.value });
  }

  async function salvar(event) {
    event.preventDefault();
    setErro("");
    setSalvando(true);
    try {
      const { data } = editando
        ? await api.put(`/produtos/${id}`, form)
        : await api.post("/produtos", form);
      navigate("/produtos", {
        state: { mensagem: `Produto "${data.nome}" ${editando ? "atualizado" : "cadastrado"}.` },
      });
    } catch (e) {
      setErro(mensagemDeErro(e));
      setSalvando(false);
    }
  }

  if (carregando) {
    return <p className="texto-suave">Carregando produto...</p>;
  }

  return (
    <section className="secao-estreita">
      <Link to="/produtos" className="voltar">&larr; Voltar para produtos</Link>
      <h1>{editando ? `Editar produto #${id}` : "Novo produto"}</h1>

      <form className="cartao" onSubmit={salvar}>
        <Alerta>{erro}</Alerta>

        <label className="campo">
          Código de barras
          <input
            name="codigoBarras"
            value={form.codigoBarras}
            onChange={alterar}
            inputMode="numeric"
            required
            autoFocus
          />
        </label>

        <label className="campo">
          Nome
          <input name="nome" value={form.nome} onChange={alterar} required />
        </label>

        <label className="campo">
          Descrição
          <textarea name="descricao" value={form.descricao} onChange={alterar} rows={4} />
        </label>

        <div className="info-estoque">
          {editando && produto ? (
            <>
              Estoque atual: <strong>{produto.estoque}</strong>.{" "}
            </>
          ) : (
            <>O produto começa com estoque 0. </>
          )}
          O estoque só muda por{" "}
          {editando ? (
            <Link to={`/produtos/${id}/movimentacoes`}>movimentações de entrada e saída</Link>
          ) : (
            "movimentações de entrada e saída"
          )}
          .
        </div>

        <div className="acoes-form">
          <Link to="/produtos" className="botao botao-secundario">Cancelar</Link>
          <button type="submit" className="botao botao-primario" disabled={salvando}>
            {salvando ? "Salvando..." : "Salvar"}
          </button>
        </div>
      </form>
    </section>
  );
};

export default ProdutoForm;

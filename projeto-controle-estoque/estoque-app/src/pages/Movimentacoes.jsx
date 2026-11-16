import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Alerta from "../components/Alerta";
import api, { mensagemDeErro } from "../services/api";

const formatoDataHora = new Intl.DateTimeFormat("pt-BR", {
  dateStyle: "short",
  timeStyle: "short",
});

// Controle de estoque de UM produto: registra entradas/saídas e lista as movimentações dele.
// Movimentações não podem ser alteradas nem excluídas, por isso a tabela não tem ações.
const Movimentacoes = () => {
  const { id } = useParams();

  const [produto, setProduto] = useState(null);
  const [movimentacoes, setMovimentacoes] = useState([]);
  const [carregando, setCarregando] = useState(true);
  const [erroCarregar, setErroCarregar] = useState("");

  const [tipo, setTipo] = useState("ENTRADA");
  const [quantidade, setQuantidade] = useState("");
  const [erro, setErro] = useState("");
  const [sucesso, setSucesso] = useState("");
  const [salvando, setSalvando] = useState(false);

  useEffect(() => {
    let ativo = true;
    // Busca o produto e as movimentações dele ao mesmo tempo
    Promise.all([api.get(`/produtos/${id}`), api.get(`/produtos/${id}/movimentacoes`)])
      .then(([respostaProduto, respostaMovimentacoes]) => {
        if (!ativo) return;
        setProduto(respostaProduto.data);
        setMovimentacoes(respostaMovimentacoes.data);
      })
      .catch((e) => ativo && setErroCarregar(mensagemDeErro(e)))
      .finally(() => ativo && setCarregando(false));
    return () => {
      ativo = false;
    };
  }, [id]);

  async function registrar(event) {
    event.preventDefault();
    setErro("");
    setSucesso("");

    const qtd = Number(quantidade);
    const descricaoTipo = tipo === "ENTRADA" ? "entrada" : "saída";
    const confirmado = window.confirm(
      `Registrar ${descricaoTipo} de ${qtd} unidade(s) de "${produto.nome}"?\n\n` +
        "Movimentações não podem ser alteradas nem excluídas depois.",
    );
    if (!confirmado) return;

    setSalvando(true);
    try {
      const { data } = await api.post(`/produtos/${id}/movimentacoes`, { tipo, quantidade: qtd });
      // A API já atualizou o estoque; aqui só refletimos na tela
      setMovimentacoes((lista) => [data, ...lista]);
      setProduto((p) => ({
        ...p,
        estoque: data.tipo === "ENTRADA" ? p.estoque + data.quantidade : p.estoque - data.quantidade,
      }));
      setQuantidade("");
      setSucesso(`${tipo === "ENTRADA" ? "Entrada" : "Saída"} de ${data.quantidade} registrada.`);
    } catch (e) {
      setErro(mensagemDeErro(e));
    } finally {
      setSalvando(false);
    }
  }

  if (carregando) {
    return <p className="texto-suave">Carregando...</p>;
  }

  if (erroCarregar) {
    return (
      <section>
        <Link to="/produtos" className="voltar">&larr; Voltar para produtos</Link>
        <Alerta>{erroCarregar}</Alerta>
      </section>
    );
  }

  return (
    <section>
      <Link to="/produtos" className="voltar">&larr; Voltar para produtos</Link>
      <h1>Controle de estoque</h1>

      <div className="resumo-produto cartao">
        <div>
          <div className="texto-suave">
            Produto #{produto.id} · <span className="mono">{produto.codigoBarras}</span>
            {!produto.ativo && <span className="etiqueta etiqueta-inativo">Inativo</span>}
          </div>
          <h2>{produto.nome}</h2>
          {produto.descricao && <p className="texto-suave">{produto.descricao}</p>}
        </div>
        <div className="estoque-atual">
          <span className="texto-suave">Estoque atual</span>
          <strong>{produto.estoque}</strong>
        </div>
      </div>

      <form className="cartao form-movimentacao" onSubmit={registrar}>
        <h2>Nova movimentação</h2>
        {!produto.ativo && (
          <Alerta tipo="info">Produto desativado não pode ser movimentado.</Alerta>
        )}
        <Alerta>{erro}</Alerta>
        <Alerta tipo="sucesso">{sucesso}</Alerta>

        <div className="linha-campos">
          <fieldset className="campo seletor-tipo" disabled={!produto.ativo}>
            <legend>Tipo</legend>
            <label className={tipo === "ENTRADA" ? "selecionado entrada" : ""}>
              <input
                type="radio"
                name="tipo"
                value="ENTRADA"
                checked={tipo === "ENTRADA"}
                onChange={(e) => setTipo(e.target.value)}
              />
              Entrada
            </label>
            <label className={tipo === "SAIDA" ? "selecionado saida" : ""}>
              <input
                type="radio"
                name="tipo"
                value="SAIDA"
                checked={tipo === "SAIDA"}
                onChange={(e) => setTipo(e.target.value)}
              />
              Saída
            </label>
          </fieldset>

          <label className="campo campo-quantidade">
            Quantidade
            <input
              type="number"
              min={1}
              step={1}
              max={tipo === "SAIDA" ? produto.estoque : undefined}
              value={quantidade}
              onChange={(e) => setQuantidade(e.target.value)}
              disabled={!produto.ativo}
              required
            />
          </label>

          <button
            type="submit"
            className="botao botao-primario"
            disabled={salvando || !produto.ativo}
          >
            {salvando ? "Registrando..." : "Registrar"}
          </button>
        </div>
      </form>

      <h2>Movimentações deste produto</h2>
      <div className="tabela-container">
        <table className="tabela">
          <thead>
            <tr>
              <th className="col-numero">ID</th>
              <th>Tipo</th>
              <th className="col-numero">Quantidade</th>
              <th>Data/hora</th>
              <th>Operador</th>
            </tr>
          </thead>
          <tbody>
            {movimentacoes.length === 0 && (
              <tr>
                <td colSpan={5} className="tabela-vazia">
                  Nenhuma movimentação registrada para este produto.
                </td>
              </tr>
            )}
            {movimentacoes.map((m) => (
              <tr key={m.id}>
                <td className="col-numero">{m.id}</td>
                <td>
                  <span className={`etiqueta ${m.tipo === "ENTRADA" ? "etiqueta-entrada" : "etiqueta-saida"}`}>
                    {m.tipo === "ENTRADA" ? "Entrada" : "Saída"}
                  </span>
                </td>
                <td className={`col-numero forte ${m.tipo === "ENTRADA" ? "texto-entrada" : "texto-saida"}`}>
                  {m.tipo === "ENTRADA" ? "+" : "−"}
                  {m.quantidade}
                </td>
                <td>{formatoDataHora.format(new Date(m.dataHora))}</td>
                <td>{m.usuario}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </section>
  );
};

export default Movimentacoes;

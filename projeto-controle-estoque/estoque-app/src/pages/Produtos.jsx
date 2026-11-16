import { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import Alerta from "../components/Alerta";
import api, { mensagemDeErro } from "../services/api";

// Tela principal: tabela com os produtos cadastrados.
// Nao existe opcao de excluir, apenas desativar/reativar.
const Produtos = () => {
  const location = useLocation();
  const [produtos, setProdutos] = useState([]);
  const [carregando, setCarregando] = useState(true);
  const [erro, setErro] = useState("");
  const [sucesso, setSucesso] = useState(location.state?.mensagem || "");
  const [busca, setBusca] = useState("");
  const [filtroStatus, setFiltroStatus] = useState("todos");

  useEffect(() => {
    let ativo = true;
    api
      .get("/produtos")
      .then(({ data }) => ativo && setProdutos(data))
      .catch((e) => ativo && setErro(mensagemDeErro(e)))
      .finally(() => ativo && setCarregando(false));
    return () => {
      ativo = false;
    };
  }, []);

  async function alterarStatus(produto) {
    const acao = produto.ativo ? "desativar" : "ativar";
    if (produto.ativo && !window.confirm(`Desativar o produto "${produto.nome}"?`)) {
      return;
    }
    setErro("");
    setSucesso("");
    try {
      const { data } = await api.patch(`/produtos/${produto.id}/${acao}`);
      setProdutos((lista) => lista.map((p) => (p.id === data.id ? data : p)));
      setSucesso(`Produto "${data.nome}" ${data.ativo ? "reativado" : "desativado"}.`);
    } catch (e) {
      setErro(mensagemDeErro(e));
    }
  }

  const termo = busca.trim().toLowerCase();
  const produtosVisiveis = produtos.filter((p) => {
    if (filtroStatus === "ativos" && !p.ativo) return false;
    if (filtroStatus === "inativos" && p.ativo) return false;
    if (!termo) return true;
    return (
      p.nome.toLowerCase().includes(termo) ||
      p.codigoBarras.toLowerCase().includes(termo) ||
      (p.descricao || "").toLowerCase().includes(termo)
    );
  });

  return (
    <section>
      <div className="titulo-pagina">
        <h1>Produtos</h1>
        <Link to="/produtos/novo" className="botao botao-primario">
          + Novo produto
        </Link>
      </div>

      <Alerta>{erro}</Alerta>
      <Alerta tipo="sucesso">{sucesso}</Alerta>

      <div className="filtros">
        <input
          type="search"
          placeholder="Buscar por nome, código de barras ou descrição"
          value={busca}
          onChange={(e) => setBusca(e.target.value)}
          aria-label="Buscar produto"
        />
        <select
          value={filtroStatus}
          onChange={(e) => setFiltroStatus(e.target.value)}
          aria-label="Filtrar por status"
        >
          <option value="todos">Todos</option>
          <option value="ativos">Somente ativos</option>
          <option value="inativos">Somente inativos</option>
        </select>
      </div>

      <div className="tabela-container">
        <table className="tabela">
          <thead>
            <tr>
              <th className="col-numero">ID</th>
              <th>Código de barras</th>
              <th>Nome</th>
              <th>Descrição</th>
              <th className="col-numero">Estoque</th>
              <th>Status</th>
              <th className="col-acoes">Ações</th>
            </tr>
          </thead>
          <tbody>
            {carregando && (
              <tr>
                <td colSpan={7} className="tabela-vazia">Carregando produtos...</td>
              </tr>
            )}
            {!carregando && produtosVisiveis.length === 0 && (
              <tr>
                <td colSpan={7} className="tabela-vazia">
                  {produtos.length === 0
                    ? "Nenhum produto cadastrado ainda."
                    : "Nenhum produto encontrado com esse filtro."}
                </td>
              </tr>
            )}
            {produtosVisiveis.map((produto) => (
              <tr key={produto.id} className={produto.ativo ? "" : "linha-inativa"}>
                <td className="col-numero">{produto.id}</td>
                <td className="mono">{produto.codigoBarras}</td>
                <td>{produto.nome}</td>
                <td className="col-descricao">{produto.descricao}</td>
                <td className="col-numero forte">{produto.estoque}</td>
                <td>
                  <span className={`etiqueta ${produto.ativo ? "etiqueta-ativo" : "etiqueta-inativo"}`}>
                    {produto.ativo ? "Ativo" : "Inativo"}
                  </span>
                </td>
                <td className="col-acoes">
                  <Link to={`/produtos/${produto.id}/movimentacoes`} className="botao botao-pequeno">
                    Movimentações
                  </Link>
                  <Link to={`/produtos/${produto.id}/editar`} className="botao botao-pequeno">
                    Editar
                  </Link>
                  <button
                    type="button"
                    className={`botao botao-pequeno ${produto.ativo ? "botao-perigo" : ""}`}
                    onClick={() => alterarStatus(produto)}
                  >
                    {produto.ativo ? "Desativar" : "Reativar"}
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </section>
  );
};

export default Produtos;

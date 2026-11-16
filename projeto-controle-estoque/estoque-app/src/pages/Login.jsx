import { useState } from "react";
import { Link, Navigate, useLocation, useNavigate, useSearchParams } from "react-router-dom";
import Alerta from "../components/Alerta";
import { useAuth } from "../contexts/useAuth";
import { mensagemDeErro } from "../services/api";

const Login = () => {
  const { login, autenticado } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const [params] = useSearchParams();

  const [email, setEmail] = useState(location.state?.email || "");
  const [senha, setSenha] = useState("");
  const [erro, setErro] = useState("");
  const [enviando, setEnviando] = useState(false);

  // Ja logado? Vai direto para os produtos
  if (autenticado) {
    return <Navigate to="/produtos" replace />;
  }

  async function entrar(event) {
    event.preventDefault();
    setErro("");
    setEnviando(true);
    try {
      await login(email, senha);
      navigate(location.state?.de || "/produtos", { replace: true });
    } catch (e) {
      setErro(mensagemDeErro(e));
    } finally {
      setEnviando(false);
    }
  }

  return (
    <div className="tela-acesso">
      <form className="cartao cartao-acesso" onSubmit={entrar}>
        <div className="acesso-cabecalho">
          <img src="/favicon.svg" alt="" width="40" height="40" />
          <h1>Controle de Estoque</h1>
          <p className="texto-suave">Entre com seu e-mail e senha de operador</p>
        </div>

        {params.get("sessao") === "expirada" && !erro && (
          <Alerta tipo="info">Sua sessão expirou. Entre novamente.</Alerta>
        )}
        {location.state?.cadastrado && !erro && (
          <Alerta tipo="sucesso">Operador cadastrado! Agora é só entrar.</Alerta>
        )}
        <Alerta>{erro}</Alerta>

        <label className="campo">
          E-mail
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            autoComplete="username"
            required
            autoFocus
          />
        </label>

        <label className="campo">
          Senha
          <input
            type="password"
            value={senha}
            onChange={(e) => setSenha(e.target.value)}
            autoComplete="current-password"
            required
          />
        </label>

        <button type="submit" className="botao botao-primario botao-largo" disabled={enviando}>
          {enviando ? "Entrando..." : "Entrar"}
        </button>

        <p className="texto-suave texto-centro">
          Primeiro acesso? <Link to="/cadastro">Cadastrar operador</Link>
        </p>
      </form>
    </div>
  );
};

export default Login;

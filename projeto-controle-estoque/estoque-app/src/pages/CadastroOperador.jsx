import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Alerta from "../components/Alerta";
import api, { mensagemDeErro } from "../services/api";

// Cadastro de um novo operador (usa POST /usuarios/cadastro, que e publico).
const CadastroOperador = () => {
  const navigate = useNavigate();
  const [form, setForm] = useState({ nome: "", email: "", senha: "", confirmacao: "" });
  const [erro, setErro] = useState("");
  const [enviando, setEnviando] = useState(false);

  function alterar(event) {
    setForm({ ...form, [event.target.name]: event.target.value });
  }

  async function cadastrar(event) {
    event.preventDefault();
    setErro("");
    if (form.senha !== form.confirmacao) {
      setErro("As senhas não conferem.");
      return;
    }
    setEnviando(true);
    try {
      await api.post("/usuarios/cadastro", {
        nome: form.nome,
        email: form.email,
        senha: form.senha,
      });
      navigate("/login", { state: { cadastrado: true, email: form.email } });
    } catch (e) {
      setErro(mensagemDeErro(e));
    } finally {
      setEnviando(false);
    }
  }

  return (
    <div className="tela-acesso">
      <form className="cartao cartao-acesso" onSubmit={cadastrar}>
        <div className="acesso-cabecalho">
          <h1>Cadastrar operador</h1>
        </div>

        <Alerta>{erro}</Alerta>

        <label className="campo">
          Nome
          <input name="nome" value={form.nome} onChange={alterar} required autoFocus />
        </label>
        <label className="campo">
          E-mail
          <input type="email" name="email" value={form.email} onChange={alterar} required />
        </label>
        <label className="campo">
          Senha
          <input
            type="password"
            name="senha"
            value={form.senha}
            onChange={alterar}
            autoComplete="new-password"
            minLength={6}
            required
          />
        </label>
        <label className="campo">
          Confirmar senha
          <input
            type="password"
            name="confirmacao"
            value={form.confirmacao}
            onChange={alterar}
            autoComplete="new-password"
            required
          />
        </label>

        <button type="submit" className="botao botao-primario botao-largo" disabled={enviando}>
          {enviando ? "Cadastrando..." : "Cadastrar"}
        </button>

        <p className="texto-suave texto-centro">
          <Link to="/login">Voltar para o login</Link>
        </p>
      </form>
    </div>
  );
};

export default CadastroOperador;

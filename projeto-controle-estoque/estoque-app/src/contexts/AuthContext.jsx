import { useState } from "react";
import api, { CHAVE_TOKEN, CHAVE_USUARIO } from "../services/api";
import { AuthContext } from "./useAuth";

// Guarda quem está logado e deixa isso disponível para qualquer componente (via useAuth).
function lerUsuarioSalvo() {
  try {
    const salvo = localStorage.getItem(CHAVE_USUARIO);
    return salvo && localStorage.getItem(CHAVE_TOKEN) ? JSON.parse(salvo) : null;
  } catch {
    return null;
  }
}

export function AuthProvider({ children }) {
  const [usuario, setUsuario] = useState(lerUsuarioSalvo);

  async function login(email, senha) {
    const { data } = await api.post("/usuarios/login", { email, senha });
    // O backend controla o acesso; o front so armazena o token recebido
    localStorage.setItem(CHAVE_TOKEN, data.token);
    const dadosUsuario = { nome: data.nome, email: data.email };
    localStorage.setItem(CHAVE_USUARIO, JSON.stringify(dadosUsuario));
    setUsuario(dadosUsuario);
  }

  function logout() {
    localStorage.removeItem(CHAVE_TOKEN);
    localStorage.removeItem(CHAVE_USUARIO);
    setUsuario(null);
  }

  return (
    <AuthContext.Provider value={{ usuario, autenticado: usuario !== null, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

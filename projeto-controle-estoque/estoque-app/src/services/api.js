import axios from "axios";

// Onde o token e os dados do operador ficam guardados no navegador
export const CHAVE_TOKEN = "estoque.token";
export const CHAVE_USUARIO = "estoque.usuario";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL || "http://localhost:8080",
  headers: { "Content-Type": "application/json" },
});

// Antes de cada requisicao: se houver token salvo, manda no header Authorization
api.interceptors.request.use((config) => {
  const token = localStorage.getItem(CHAVE_TOKEN);
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Depois de cada resposta: 401 significa token ausente, invalido ou expirado.
// Limpa o login salvo e manda o operador de volta para a tela de login.
api.interceptors.response.use(
  (resposta) => resposta,
  (erro) => {
    const ehLogin = erro.config?.url?.includes("/usuarios/login");
    if (erro.response?.status === 401 && !ehLogin) {
      localStorage.removeItem(CHAVE_TOKEN);
      localStorage.removeItem(CHAVE_USUARIO);
      window.location.assign("/login?sessao=expirada");
    }
    return Promise.reject(erro);
  },
);

// Transforma qualquer erro do axios em uma mensagem para mostrar na tela.
// A API sempre responde erros no formato { "mensagem": "..." }.
export function mensagemDeErro(erro) {
  if (erro.response?.data?.mensagem) {
    return erro.response.data.mensagem;
  }
  if (!erro.response) {
    return "Não foi possível conectar à API. Verifique se o backend está rodando.";
  }
  return `Erro inesperado (HTTP ${erro.response.status}).`;
}

export default api;

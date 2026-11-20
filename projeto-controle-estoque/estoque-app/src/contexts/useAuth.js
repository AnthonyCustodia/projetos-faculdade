import { createContext, useContext } from "react";

// Guarda quem está logado. O valor é fornecido pelo <AuthProvider> (AuthContext.jsx).
export const AuthContext = createContext(null);

// Atalho: const { usuario, autenticado, login, logout } = useAuth();
export function useAuth() {
  return useContext(AuthContext);
}

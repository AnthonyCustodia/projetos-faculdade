import { Navigate, Outlet, useLocation } from "react-router-dom";
import { useAuth } from "../contexts/useAuth";

// Envolve as rotas que exigem login. Sem login, redireciona para /login
// e lembra a pagina que o operador tentou abrir.
const RotaPrivada = () => {
  const { autenticado } = useAuth();
  const location = useLocation();

  if (!autenticado) {
    return <Navigate to="/login" replace state={{ de: location.pathname }} />;
  }

  return <Outlet />;
};

export default RotaPrivada;

import { Link, Outlet, useNavigate } from "react-router-dom";
import { useAuth } from "../contexts/useAuth";

// Cabecalho comum das telas logadas. O conteudo de cada tela aparece no <Outlet />.
const Layout = () => {
  const { usuario, logout } = useAuth();
  const navigate = useNavigate();

  function sair() {
    logout();
    navigate("/login", { replace: true });
  }

  return (
    <>
      <header className="topo">
        <Link to="/produtos" className="marca">
          <img src="/favicon.svg" alt="" width="24" height="24" />
          Controle de Estoque
        </Link>
        <div className="topo-usuario">
          <span title={usuario?.email}>{usuario?.nome}</span>
          <button type="button" className="botao botao-secundario" onClick={sair}>
            Sair
          </button>
        </div>
      </header>
      <main className="conteudo">
        <Outlet />
      </main>
    </>
  );
};

export default Layout;

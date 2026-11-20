import { Navigate, Route, Routes } from "react-router-dom";

import Layout from "./components/Layout";
import RotaPrivada from "./components/RotaPrivada";
import Login from "./pages/Login";
import CadastroOperador from "./pages/CadastroOperador";
import Produtos from "./pages/Produtos";
import ProdutoForm from "./pages/ProdutoForm";
import Movimentacoes from "./pages/Movimentacoes";
import NaoEncontrado from "./pages/NaoEncontrado";

const App = () => {
  return (
    <Routes>
      {/* Rotas publicas */}
      <Route path="/login" element={<Login />} />
      <Route path="/cadastro" element={<CadastroOperador />} />

      {/* Rotas que exigem login */}
      <Route element={<RotaPrivada />}>
        <Route element={<Layout />}>
          <Route path="/" element={<Navigate to="/produtos" replace />} />
          <Route path="/produtos" element={<Produtos />} />
          <Route path="/produtos/novo" element={<ProdutoForm />} />
          <Route path="/produtos/:id/editar" element={<ProdutoForm />} />
          <Route path="/produtos/:id/movimentacoes" element={<Movimentacoes />} />
          <Route path="*" element={<NaoEncontrado />} />
        </Route>
      </Route>
    </Routes>
  );
};

export default App;

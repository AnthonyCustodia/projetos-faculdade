import { Routes, Route, Link, NavLink } from "react-router-dom";

import Home from "./components/Home";
import About from "./components/About";
import Contact from "./components/Contact";
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";
import Profile from "./components/Profile";
import Settings from "./components/Settings";
import TaskDetails from "./components/TaskDetails";
import NotFound from "./components/NotFound";

const App = () => {
  return (
    <div>
      {/* Tudo que está fora do Routes se repete em todas as páginas */}
      <nav>
        <ul>
          <li>
            <Link to="/">Início</Link>
          </li>
          <li>
            <Link to="/about">Sobre</Link>
          </li>
          {/* NavLink: adiciona uma classe quando a rota está ativa */}
          <li>
            <NavLink
              to="/contact"
              className={({ isActive }) => (isActive ? "active-link" : "")}
            >
              Contato
            </NavLink>
          </li>
          <li>
            <Link to="/login">Login</Link>
          </li>
          <li>
            <Link to="/dashboard">Dashboard</Link>
          </li>
        </ul>
      </nav>

      {/* Roteamento dinâmico */}
      <div>
        <h2>Tarefas</h2>
        <Link to={`/tasks/1`}>Tarefa 1</Link>
        <Link to={`/tasks/2`}>Tarefa 2</Link>
        <Link to={`/tasks/3`}>Tarefa 3</Link>
      </div>

      {/* Links para rotas inexistentes (cai no 404) */}
      <div>
        <h2>Itens</h2>
        <Link to={`/items/1`}>Item 1</Link>
        <Link to={`/items/2`}>Item 2</Link>
      </div>

      {/* Tudo que está dentro do Routes vai sendo trocado */}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/contact" element={<Contact />} />
        <Route path="/login" element={<Login />} />

        {/* Rotas aninhadas para o Dashboard (renderizadas no <Outlet />) */}
        <Route path="/dashboard" element={<Dashboard />}>
          <Route path="profile" element={<Profile />} />
          <Route path="settings" element={<Settings />} />
        </Route>

        {/* Roteamento dinâmico */}
        <Route path="/tasks/:taskId" element={<TaskDetails />} />

        {/* 404 - Not Found */}
        <Route path="*" element={<NotFound />} />
      </Routes>
    </div>
  );
};

export default App;

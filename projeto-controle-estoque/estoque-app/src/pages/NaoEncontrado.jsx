import { Link } from "react-router-dom";

const NaoEncontrado = () => {
  return (
    <section>
      <h1>Página não encontrada</h1>
      <p className="texto-suave">O endereço acessado não existe.</p>
      <Link to="/produtos" className="botao botao-primario">Ir para produtos</Link>
    </section>
  );
};

export default NaoEncontrado;

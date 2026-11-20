// Caixa de mensagem. tipo: "erro" | "sucesso" | "info"
const Alerta = ({ tipo = "erro", children }) => {
  if (!children) return null;

  return (
    <div className={`alerta alerta-${tipo}`} role={tipo === "erro" ? "alert" : "status"}>
      {children}
    </div>
  );
};

export default Alerta;

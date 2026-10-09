// Só mostra a mensagem se vier algo na prop; senão retorna null
function MsgAlerta({ mensagem }) {
  return mensagem ? <div>Aviso: {mensagem}</div> : null
}

export default MsgAlerta

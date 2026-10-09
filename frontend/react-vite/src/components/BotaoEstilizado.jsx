// 1ª forma de estilizar: objeto de estilo passado no atributo style
function BotaoEstilizado() {
  // class -> className
  // for -> htmlFor

  const estiloBotao = {
    backgroundColor: '#333',
    color: '#fff',
    padding: '15px 32px',
    cursor: 'pointer', // (no slide estava "cursos": o correto é "cursor")
  }

  return <button style={estiloBotao}>Clique Aqui CSS</button>
}

export default BotaoEstilizado

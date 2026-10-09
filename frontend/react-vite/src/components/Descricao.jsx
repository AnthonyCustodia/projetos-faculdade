// Props com arrow function (acessando props.nome e props.idade)
const Descricao = (props) => {
  return (
    <div>
      Seu Nome é: {props.nome} com idade de {props.idade}
    </div>
  )
}

export default Descricao

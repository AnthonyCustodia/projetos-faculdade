/**
 * AULA 02 - Props: reaproveitando o mesmo componente
 * --------------------------------------------------
 * A diferença para o `Descricao` é a intenção: aqui o objetivo é
 * mostrar que o mesmo componente serve para muitas coisas, desde que
 * os dados cheguem pelas props.
 *
 * Troque os valores no `App.jsx` e veja o resultado mudar:
 *   <Cachorro nome="Duque" raca="SRD" />
 *   <Cachorro nome="Mel" raca="Poodle" />
 *
 * Nada aqui está "amarrado" a um cachorro específico. O componente
 * desenha; quem decide o conteúdo é quem o utiliza. É essa
 * reutilização que evita duplicar marcação.
 *
 * `raca = 'não informada'` é um valor padrão, para o componente não
 * quebrar se essa prop não for enviada.
 */
const Cachorro = ({ nome, raca = 'não informada' }) => {
  return <p>Cachorro se chama {nome}, da raça {raca}.</p>
}

export default Cachorro

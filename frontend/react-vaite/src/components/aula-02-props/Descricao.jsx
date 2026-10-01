/**
 * AULA 02 - Props: passando dados para um componente
 * -------------------------------------------------
 * Props (abreviação de "properties") são os parâmetros de um
 * componente. O pai "entrega" valores ao filho pela tag:
 *
 *   <Descricao nome="Pedro" idade={36} />
 *
 * Repare na diferença de sintaxe:
 *   - `nome="Pedro"`  -> string literal, vai entre aspas
 *   - `idade={36}`    -> expressão JavaScript, vai entre chaves
 *
 * Quando o valor é um número, booleano ou objeto, SEMPRE use as
 * chaves. `idade="36"` chegaria como texto, e `idade={36}` chega
 * como número de verdade — isso importa em comparações e cálculos.
 *
 * Este componente usa **desestruturação** nos parâmetros:
 * `({ nome, idade })` é o mesmo que escrever `props => ...` e usar
 * `props.nome`. A diferença é que a desestruturação já extrai os
 * campos, deixando o JSX mais limpo.
 *
 * `idade = 0` define um **valor padrão**: se o pai esquecer de
 * passar `idade`, usamos 0 em vez de `undefined`. Sem isso, qualquer
 * cálculo com `idade` quebraria.
 */
const Descricao = ({ nome, idade = 0 }) => {
  return <p>Seu nome é: {nome}, com idade de {idade} anos.</p>
}

export default Descricao

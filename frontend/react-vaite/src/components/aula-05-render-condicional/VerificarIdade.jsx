/**
 * AULA 05 - Operador ternário
 * ---------------------------
 * O operador ternário é a forma completa de escolher entre dois valores:
 *
 *    condição ? valorSeVerdadeiro : valorSeFalso
 *
 * Ele resolve o problema do atalho `&&` visto no `RenderConditional`:
 * quando a condição é o número `0`, o `&&` devolve esse `0` e o React
 * desenha o zero na tela. O ternário não sofre com isso, porque os dois
 * ramos são escritos explicitamente.
 *
 * Repare que a comparação `valor >= 18` é a própria condição do ternário,
 * escrita dentro dele. Não existe variável intermediária guardaando o
 * resultado, porque o ponto da aula é ver o operador operando sobre a
 * comparação.
 *
 * Os dois ramos devolvem texto, então este componente sempre mostra algo.
 * A variante com `: null` no ramo falso (usada quando não há nada a
 * exibir) está no `RenderConditional`.
 */
const VerificarIdade = ({ idade = 0 }) => {
  // `idade` pode chegar como texto se vier de um <input>. Sem o Number(),
  // a comparação seria feita entre duas strings, e o JavaScript passa a
  // ordenar por caractere: "9" >= "18" seria verdadeiro, porque "9" vem
  // depois de "1" no alfabeto. Com Number() a comparação é numérica de
  // verdade.
  const valor = Number(idade)

  return (
    <p>
      {valor >= 18
        ? `Operador maior de idade (${valor} anos).`
        : `Operador menor de idade (${valor} anos).`}
    </p>
  )
}

export default VerificarIdade

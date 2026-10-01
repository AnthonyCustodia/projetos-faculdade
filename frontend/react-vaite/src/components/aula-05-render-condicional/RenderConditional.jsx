/**
 * AULA 05 - Renderização condicional com &&
 * ------------------------------------------
 * Dentro do JSX, `{condição && <JSX />}` é o atalho mais comum para
 * "mostrar algo só quando a condição for verdadeira".
 *
 * Como funciona: se `condição` for `true`, o `&&` devolve o que está
 * à direita (o JSX). Se for `false`, devolve o próprio `false` — e o
 * React simplesmente não renderiza `false` na tela.
 *
 *    user = "Kaique"  ->  true  &&  <h1>...</h1>  ->  mostra o <h1>
 *    user = undefined ->  false &&  <h1>...</h1>  ->  não mostra nada
 *
 * ------------------------------------------------------------------
 * CUIDADO COM O "0"
 *
 * O `&&` NÃO converte para booleano: ele devolve o segundo valor
 * quando o primeiro é "falsy". Se a condição for o número `0`, o
 * JavaScript faz `0 && <JSX/>`, que resulta em `0` — e o React
 * renderiza aquele zero na tela!
 *
 * Teste trocando a prop `nome` do pai para `nome={0}` e veja o "0"
 * aparecer. A correção é usar o operador ternário, ensinado no
 * `VerificarIdade`:
 *
 *    {user ? <h1>Bem-vindo: {user}</h1> : null}
 *
 * ------------------------------------------------------------------
 * A PROP E O NOME DO COMPONENTE
 *
 * Este componente espera uma prop chamada `user`. O `App.jsx` passa
 * `nome="Kaique"`, que é outro nome — por isso `user` chega
 * `undefined` e nada aparece. Props não fazem "match" por posição
 * nem por semelhança de nome: cada uma é procurada pelo nome exato
 * que foi escrito na tag.
 */
const RenderConditional = ({ user }) => {
  return <div>{user && <h3>Bem-vindo: {user}!</h3>}</div>
}

export default RenderConditional

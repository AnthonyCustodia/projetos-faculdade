/**
 * AULA 01 - Componentes: interpolação de valores no JSX
 * ------------------------------------------------------
 * Dentro do JSX, `{ ... }` abre uma "fresta" para escrever JavaScript.
 * É por essa fresta que passamos valores do nosso código para o que
 * aparece na tela.
 *
 * Compare com HTML puro: escrevemos `<div>Bom dia Pedro</div>`.
 * Em JSX escrevemos `<div>Bom dia {nome}</div>`, e o `{nome}` é
 * substituído pelo valor da variável.
 *
 * Note também que o componente **não precisa** da tag `<div>` para
 * agrupar conteúdo. React permite retornar vários irmãos desde que
 * eles estejam dentro de um Fragment: `<> ... </>`. O Fragment não
 * gera nenhum elemento no HTML, então não interfere no CSS.
 */
const BomDia = () => {
  // Variável comum do JavaScript. Ainda NÃO é estado do React:
  // se mudasse sozinha, a tela não atualizaria (veja a Aula 04).
  const nome = 'Pedro'

  return <div>Bom dia, {nome}!</div>
}

export default BomDia

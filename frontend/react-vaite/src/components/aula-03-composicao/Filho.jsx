/**
 * AULA 03 - Composição: o componente filho
 * ----------------------------------------
 * O `Filho` é independente: não sabe quem o renderizou e não depende
 * de nada. Essa independência é o que permite reutilizá-lo em vários
 * lugares.
 *
 * Repare que não há `import` de `Pai` aqui — a dependência é sempre
 * de cima para baixo. O filho nunca importa o pai; quem usa o `Pai` é
 * que traz o `Filho` junto.
 */
const Filho = () => {
  return <p>Componente Filho (renderizado dentro do Pai)</p>
}

export default Filho

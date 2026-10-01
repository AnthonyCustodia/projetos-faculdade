/**
 * AULA 01 - Componentes: o que é um componente
 * --------------------------------------------
 * Um componente React é, no fim das contas, uma função JavaScript que
 * devolve JSX. O JSX parece HTML, mas na verdade vira chamadas de
 * `React.createElement(...)` em tempo de compilação.
 *
 * Três regras de um componente:
 *   1. O nome começa com letra maiúscula. O JSX trata toda tag
 *      minúscula (`<div>`) como HTML nativo e toda tag maiúscula
 *      (`<Welcome />`) como um componente seu. Se você errar a
 *      maiúscula, o React renderiza a tag vazia sem avisar.
 *   2. Tem exatamente um ponto de retorno.
 *   3. Pode ser declarado com `function` ou como `const` seta.
 *      Neste projeto usamos `const` seta em todos os componentes,
 *      para ficar consistente.
 *
 * Não precisamos importar `React` para usar JSX: o plugin do Vite já
 * cuida do "automatic JSX runtime". Por isso `import React from 'react'`
 * aqui seria código morto.
 */
const Welcome = () => {
  return <h2>Olá, mundo!</h2>
}

export default Welcome

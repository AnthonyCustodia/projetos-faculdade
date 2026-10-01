import { useState } from 'react'

/**
 * AULA 04 - useState: estado que redesenha a tela
 * ----------------------------------------------
 * Variáveis comuns (como `const nome = 'Pedro'` da Aula 01) não
 * avisam o React quando mudam. O `useState` resolve isso: ele
 * guarda um valor E avisa o React para redesenhar o componente
 * quando esse valor mudar.
 *
 * A destruturação `const [count, setCount] = useState(0)` nos dá
 * duas coisas:
 *   - `count`     -> o valor atual (para exibir)
 *   - `setCount`  -> a função que muda o valor
 *
 * O `0` é o valor inicial. Ele só é usado na primeira renderização;
 * nas seguintes o React já sabe o valor guardado.
 *
 * ------------------------------------------------------------------
 * POR QUE USAR A FORMA `c => c + 1` E NÃO `count + 1`?
 *
 * Escrever `setCount(count + 1)` funciona, mas depende de `count`
 * ser o valor atualizado no momento do clique. Se dois cliques
 * fossem disparados antes do React redesenhar, os dois leriam o
 * mesmo `count` velho e o incremento seria perdido.
 *
 * A forma `setCount(c => c + 1)` entrega ao React a RECEITA da
 * alteração, e não o valor. Assim o React aplica cada atualização
 * em sequência, mesmo que várias aconteçam no mesmo ciclo. Essa é a
 * forma correta sempre que o novo valor depender do anterior.
 *
 * `useState` retorna um array; a ordem dos elementos é o que
 * distingue `count` de `setCount`. Por isso o padrão de nomenclatura
 * é `[algo, setAlgo]`.
 */
const Contador = () => {
  const [count, setCount] = useState(0)

  return (
    <div className="contador">
      {/* O `{count}` aqui é o que faz a tela atualizar a cada clique. */}
      <p>Você clicou {count} {count === 1 ? 'vez' : 'vezes'}.</p>

      <button type="button" onClick={() => setCount((c) => c + 1)}>
        Somar
      </button>

      <button
        type="button"
        // Impede o contador de ficar negativo. `c => Math.max(0, c - 1)`
        // continua sendo uma "receita", então o React aplica na ordem.
        onClick={() => setCount((c) => Math.max(0, c - 1))}
      >
        Subtrair
      </button>

      <p>
        <small>Exercício: troque `count + 1` por `count * 2` e veja o que acontece.</small>
      </p>
    </div>
  )
}

export default Contador

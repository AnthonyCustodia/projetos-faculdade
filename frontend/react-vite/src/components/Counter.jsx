import { useState } from 'react'

function Counter() {
  // [consultar, alterar]
  const [count, setCount] = useState(1)

  return (
    <div>
      <p>Voce clicou: {count} vezes</p>
      <button onClick={() => setCount(count + 1)}>Clique aqui</button>
    </div>
  )
}

export default Counter

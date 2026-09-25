import { useState } from 'react'

const Contador = () => {
  const [count, setCount] = useState(0)

  return (
    <div>
      <h1>Contador</h1>
      <p>Voce clicou {count} vezes</p>
      <button onClick={() => setCount((currentCount) => currentCount + 1)}>
        Mais
      </button>
      <button
        onClick={() => setCount((currentCount) => currentCount - 1)}
        disabled={count === 0}
      >
        Menos
      </button>
    </div>
  )
}

export default Contador

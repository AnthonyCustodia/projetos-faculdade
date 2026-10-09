import { useState } from 'react'

// Exemplo do slide: estado 'nome' atualizado a cada digitação
function MeuComponente() {
  const [nome, setNome] = useState('') // Cria variável de estado 'nome' com valor inicial ''

  function handleNomeChange(event) {
    setNome(event.target.value) // Atualiza o valor do estado 'nome' com o valor digitado
  }

  return (
    <div>
      <input type="text" value={nome} onChange={handleNomeChange} />
      <p>Olá, {nome}!</p>
    </div>
  )
}

export default MeuComponente

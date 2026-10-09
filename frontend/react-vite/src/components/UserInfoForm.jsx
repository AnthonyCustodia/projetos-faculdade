import { useState } from 'react'

function UserInfoForm() {
  // Podemos ter vários useState no mesmo componente
  const [name, setName] = useState('')
  const [email, setEmail] = useState('')

  const handleSubmit = (event) => {
    event.preventDefault()
    console.log(name, email)
  }

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={name}
        onChange={(e) => setName(e.target.value)}
        placeholder="Digite seu nome"
      />
      <input
        type="text"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        placeholder="Digite seu E-Mail"
      />
      <button type="submit">Enviar</button>
    </form>
  )
}

export default UserInfoForm

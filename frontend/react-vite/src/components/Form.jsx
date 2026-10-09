import { useState } from 'react'

function Form() {
  const [value, setValue] = useState('')

  const handleSubmit = (evento) => {
    evento.preventDefault() // impede o recarregamento da página
    console.log('Formulario Enviado', value)
  }

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={value}
        onChange={(e) => setValue(e.target.value)}
        placeholder="Preencher o campo"
      />
      <button type="submit">Enviar</button>
    </form>
  )
}

export default Form

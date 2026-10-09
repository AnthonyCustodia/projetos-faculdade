function Button() {
  const handleClick = (num) => {
    console.log('Clicou', num)
  }

  return (
    <div>
      {/* Sem parâmetro: passa a função direto. (o React envia o evento como 1º argumento) */}
      <button onClick={handleClick}>Eventos</button>
      {/* Com parâmetro: precisa de uma função anônima */}
      <button onClick={() => handleClick(5)}>Eventos 2</button>
    </div>
  )
}

export default Button

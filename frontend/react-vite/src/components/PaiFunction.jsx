import FilhoFunction from './FilhoFunction'

function PaiFunction() {
  const handleChildClick = () => {
    console.log('Clicou no botão do elemento Filho!')
  }

  return (
    <div>
      <button onClick={handleChildClick}>Pai Function</button>
      <FilhoFunction onChildClick={handleChildClick} />
    </div>
  )
}

export default PaiFunction

// O filho executa uma função que está no componente pai (recebida via props)
function FilhoFunction({ onChildClick }) {
  return (
    <div>
      <button onClick={onChildClick}>Filho Function</button>
    </div>
  )
}

export default FilhoFunction

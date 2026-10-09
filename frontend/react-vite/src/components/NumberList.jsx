// Renderização de lista com map + key (identificador único de cada elemento)
function NumberList({ numbers }) {
  return (
    <ul>
      {numbers.map((number, index) => (
        <li id={index.toString()} key={index.toString()}>
          {number}
        </li>
      ))}
    </ul>
  )
}

export default NumberList

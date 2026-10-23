import React from 'react'
 
const FilmList = () => {
 
    const filmes = [
        { titulo: "Goodfellas", diretor: "Martin Scorsese" },
        { titulo: "The Godfather", diretor: "Francis Ford Coppola" },
        { titulo: "Eyes wide Shut", diretor: "Stanlay Kubrick"}
    ];
 
  return (
    <div>
        <ul>
            { filmes.map((filmes) =>  
                <li key={filmes}>
                    <li className='titulo'>{filmes.titulo}</li>
                    <li className='list'>{filmes.diretor}</li>
                </li>
            )}
           
        </ul>
    </div>
  )
}
 
export default FilmList
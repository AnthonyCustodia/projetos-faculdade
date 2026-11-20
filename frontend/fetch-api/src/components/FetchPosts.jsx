import { useEffect, useState } from 'react'

// GET usando a Fetch API (nativa do navegador, não precisa instalar nada).
const FetchPosts = () => {
  const [posts, setPosts] = useState([])
  const [error, setError] = useState('')

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await fetch('https://jsonplaceholder.typicode.com/posts')
        // O fetch só rejeita em falha de rede: erros HTTP (404, 500...) precisam ser checados no response.ok.
        if (!response.ok) {
          throw new Error('Erro ao buscar os posts: ' + response.status)
        }
        // No fetch a conversão para JSON é manual.
        const data = await response.json()
        setPosts(data)
      } catch (error) {
        setError(error.message)
      }
    }

    fetchPosts()
  }, []) // [] = executa só uma vez, quando o componente aparece na tela.

  return (
    <div>
      <h1>Posts (Fetch)</h1>
      {error ? (
        <p>Erro: {error}</p>
      ) : (
        posts.map((post) => (
          <div key={post.id} className="post">
            <h2>{post.title}</h2>
            <p>{post.body}</p>
          </div>
        ))
      )}
    </div>
  )
}

export default FetchPosts

import { useState, useEffect } from 'react'
import axios from 'axios'

// O mesmo GET do FetchPosts, agora com Axios (biblioteca externa: npm i axios).
const AxiosPosts = () => {
  const [posts, setPosts] = useState([])
  const [error, setError] = useState('')

  useEffect(() => {
    const fetchPostsAxios = async () => {
      try {
        // O Axios já converte o JSON (response.data) e rejeita sozinho em erros HTTP.
        const response = await axios.get('https://jsonplaceholder.typicode.com/posts')
        setPosts(response.data)
      } catch (error) {
        setError('Erro ao buscar os posts: ' + error.message)
      }
    }

    fetchPostsAxios()
  }, [])

  return (
    <div>
      <h1>Posts (Axios)</h1>
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

export default AxiosPosts

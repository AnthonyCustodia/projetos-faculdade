import { useEffect, useState } from 'react'
import axios from 'axios'
import PostForm from './PostForm'

// Lista os posts e controla qual está sendo editado. As requisições de
// escrita ficam no PostForm, que devolve o resultado via onSuccess.
const PostManager = () => {
  const [posts, setPosts] = useState([])
  const [selectedPost, setSelectedPost] = useState(null)
  const [isEditing, setIsEditing] = useState(false)

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get('https://jsonplaceholder.typicode.com/posts')
        setPosts(response.data.slice(0, 5)) // Limitando a 5 posts para o exemplo
      } catch (error) {
        console.error('Erro ao buscar postagens:', error)
      }
    }

    fetchPosts()
  }, [])

  // Atualiza a lista local conforme a operação feita no PostForm.
  // (O JSONPlaceholder é uma API falsa: responde como se salvasse, mas não grava nada.)
  const handleSuccess = (post, operation) => {
    if (operation === 'add') {
      // Os três pontos (spread) espalham os posts atuais no novo array, depois do post novo.
      setPosts((currentPosts) => [post, ...currentPosts])
    } else if (operation === 'update') {
      setPosts((currentPosts) =>
        currentPosts.map((p) => (p.id === post.id ? post : p))
      )
      setSelectedPost(null)
      setIsEditing(false)
    } else if (operation === 'delete') {
      setPosts((currentPosts) => currentPosts.filter((p) => p.id !== post.id))
      setSelectedPost(null) // Reseta o post selecionado
      setIsEditing(false) // Sai do modo de edição
    }
  }

  const handleEdit = (post) => {
    setSelectedPost(post)
    setIsEditing(true)
  }

  const handleCancelEdit = () => {
    setSelectedPost(null)
    setIsEditing(false)
  }

  return (
    <div>
      <h1>Gerenciador de Postagens</h1>
      {/* A key muda a cada post selecionado: o React recria o formulário com os dados certos. */}
      <PostForm
        key={isEditing ? selectedPost.id : 'novo'}
        post={isEditing ? selectedPost : null}
        onSuccess={handleSuccess}
      />
      {isEditing && <button onClick={handleCancelEdit}>Cancelar Edição</button>}

      <h2>Postagens</h2>
      {posts.map((post) => (
        <div key={post.id} className="post">
          <h3>{post.title}</h3>
          <p>{post.body}</p>
          <button onClick={() => handleEdit(post)}>Editar</button>
        </div>
      ))}
    </div>
  )
}

export default PostManager

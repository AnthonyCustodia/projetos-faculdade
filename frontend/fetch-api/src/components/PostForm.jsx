import { useState } from 'react'
import axios from 'axios'

const API_URL = 'https://jsonplaceholder.typicode.com/posts'

// Formulário usado para criar (POST), atualizar (PUT) e deletar (DELETE).
// post: o post em edição (null = criando um novo).
// onSuccess(post, operacao): avisa o PostManager para atualizar a lista.
const PostForm = ({ post, onSuccess }) => {
  const [title, setTitle] = useState(post?.title || '')
  const [body, setBody] = useState(post?.body || '')
  // Obs.: quando o post selecionado muda, o PostManager troca a "key" deste componente,
  // e o React o recria com os campos já preenchidos (ou vazios). Por isso não precisamos
  // de um useEffect com setTitle/setBody (o lint do React desaconselha setState em efeito).

  const handleSubmit = async (event) => {
    event.preventDefault() // evita o recarregamento da página
    const newPost = { title, body, userId: 1 }

    try {
      if (post) {
        // PUT: substitui o post existente
        const response = await axios.put(`${API_URL}/${post.id}`, newPost)
        onSuccess(response.data, 'update')
      } else {
        // POST: cria um novo post
        const response = await axios.post(API_URL, newPost)
        onSuccess(response.data, 'add')
      }
      setTitle('')
      setBody('')
    } catch (error) {
      console.error('Erro ao enviar postagem:', error)
    }
  }

  const handleDelete = async () => {
    try {
      // DELETE: remove o post
      await axios.delete(`${API_URL}/${post.id}`)
      onSuccess(post, 'delete')
    } catch (error) {
      console.error('Erro ao deletar postagem:', error)
    }
  }

  return (
    <form onSubmit={handleSubmit} className="post-form">
      <div>
        <input
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Título"
          required
        />
      </div>
      <div>
        <textarea
          value={body}
          onChange={(e) => setBody(e.target.value)}
          placeholder="Digite o Conteúdo..."
          required
        />
      </div>
      <button type="submit">{post ? 'Atualizar' : 'Adicionar'}</button>
      {post && (
        <button type="button" onClick={handleDelete}>
          Deletar
        </button>
      )}
    </form>
  )
}

export default PostForm

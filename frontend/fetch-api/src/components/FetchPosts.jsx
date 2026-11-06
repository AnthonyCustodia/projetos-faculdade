import React, { useEffect, useState } from 'react'
import { Route, BrowserRouter, Routes, Link } from 'react-router-dom'

const FetchPosts = () => {
    const [posts, setPosts] = useState([]);
    const [error, setError] = useState("");

    useEffect(() => {
        const FetchPosts = async () => {
            try {
                const response = await
                fetch("https://jsonplaceholder.typecode.com/posts")
                if (!response.ok) {
                    throw new Error("Erro ao buscar os posts" + response.status)
                }
                const data = await response.json()
                setPosts(data)
            } catch (error) {
                setError(error)
            }
        }
    })

  return (
    <div>
      <h1>FetchPosts</h1>
      {error ? (
        <p>Erro: {erro}</p>
      ) : (
        posts.map((post) => (
            <div key={post.id}>
                <h2>{post.title}</h2>
                <p>{post.body}</p>
            </div>
        ))
      )
    }
    </div>
  )
}

export default FetchPosts

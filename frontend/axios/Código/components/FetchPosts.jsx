import React, { useEffect, useState } from 'react'

const FetchPosts = () => {
    const [posts, setPosts] = useState([]);
    const [error, setError] = useState("");

    useEffect(() => {
        const FetchPosts = async () =>{
            try {
                 const response = await 
                 fetch( "https://jsonplaceholder.typicode.com/posts" );
                 if (!response.ok){
                    throw new Error( "Erro ao buscar os posts" + response.statusText)
                 }
                 const data = await response.json();
                 setPosts(data)
            } catch (error) {
                setError(error.message)
            }
        };
        FetchPosts();
    }, []);

  return (
    <div>
        <h1>FetchPosts</h1>
        {error ? (
            <p>Erro: {erro}</p>
        ) : (
            posts.map((post)=>(
                <div  key = {post.id}>
                    <h2>{post.title}</h2>
                    <p>{post.body}</p>
                </div>
            )
            )
        )}
    </div>
  )
}

export default FetchPosts
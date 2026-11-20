import React, { use, useEffect, useState } from 'react'
import axios from 'axios'


const PostManager = () => {
    const [post,setPost] = useState([]);
    const [selectedPost, setSelectedPost] = useState(null);
    const [isEditing, setIsEditing] = useState(false);

    useEffect( () => {
        fetchPosts()
    },[]);

    const fetchPosts = async () =>{
        try{
            const response = await axios.get(
                'https://jsonplaceholder.typicode.com/posts'
            )
            setPost(response.data.slice(0,5));
        }
        catch(error){
            console.log("Erro ao buscar dados ", error );
        }
    }

    const handleSuccess =(post, operation) =>{
        if (operation === "add"){
            setPost((currentPost)=> [post, ...currentPost])
        }else if(operation === "update"){
            setPost((currentPost)=>
                currentPost.map((p)=> (p.id === post.id ? post:p))
            );
        }else if(operation === "delete"){
            setPost((currentPost) => 
                currentPost.filter((p)=>p.id !== post.id));
            setSelectedPost(null);
            setIsEditing(false);
        }
    }

    const handleEdit = () =>{
        setSelectedPost(null);
        setIsEditing(false);
    }
    const handleCancelEdit = () =>{
        setIsEditing(false);
        setSelectedPost(null);
    }
    
  return (
    <div>
        <h1>Gerenciador de Post</h1>
        <PostForm
            post = {isEditing ? selectedPost : null}
            onSuccess={handleSuccess}
        />
        {isEditing && 
        <button 
            onClick={handleCancelEdit}>Cancelar Edição
        </button>}
        <h2>Postagens</h2>
        {posts.map((post) =>{
            <div key = {post.id}>
                <h3>{post.title}</h3>
                <p>{post.body}</p>
                <button onClick={() => handleEdit(post)}>Editar</button>
            </div>
        }
    )}
    </div>
  )
}

export default PostManager
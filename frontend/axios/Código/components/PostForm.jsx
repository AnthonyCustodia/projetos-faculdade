import React, { useEffect, useState } from 'react'
import axios from 'axios'
const PostForm = () => {
    const [title, setTitle] = useState(post?.title || "");
    const [body, setBody] = useState(post?.body || "");

    useEffect(() =>{
        setTitle(post?.title);
        setBody(post?.body)
    }, [post]);

    const handleSubmit = async (event) =>{
        event.preventDefault();
        const newPost = {title, body, userId: 1}
        try {
            if(post){
              const response = await axios.put(
               `https://jsonplaceholder.typecode/posts/${post.id}`,
                 newPost
              )
              onSuccess(response.data, "update");
            } else{
                const response = await axios.post(
                    "https://jsonplaceholder.typecode/posts",
                    newPost
                )
                onSuccess(response.data, "add")
            }
            setTitle("");
            setBody("");
        } catch (error) {
            console.log("Erro ao enviar postagens", error)
        }
    }

    const handleDelete = 

  return (
    <div>PostForm</div>
  )
}

export default PostForm
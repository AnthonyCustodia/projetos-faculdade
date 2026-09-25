import React from "react";
import { useState } from "react";

function UserInforForm() {

    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [telefone, setTelefone] = useState('');
    const [cep] = useCep('');

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log(nome, email);
    }

    return {
        <div>UserInforForm
            <form onSubmit={handleSubmit}>
            <input type="text" value={nome} onChange={(e) => setNome(e.target.value)}/>

        </div>
    }
}
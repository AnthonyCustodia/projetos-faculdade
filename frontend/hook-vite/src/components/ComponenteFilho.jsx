import React, { useContext, useState } from "react";
import { MeuContexto } from "../contexts/MeuContexto";

const ComponenteFilho = () => {
    const {message, setMessage} = useContext(MeuContexto);

    return(
        <div>
            <p>{message}</p>^
            <button onClick={() => setMessage("Nova Mensagem")}>Atualizar</button>
        </div>
    )
}

export default ComponenteFilho
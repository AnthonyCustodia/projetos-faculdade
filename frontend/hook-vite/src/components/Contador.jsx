import React from "react";
import { useReducer } from "react";

const estadoInicial = {contador: 0}

function reducer(estado, acao) {
    switch(acao.tipo) {
        case "incrementar" : return {contador: estado.contador + 1}
        case "decrementar" : return {contador: estado.contador - 1}
        case "resetar" : return {contador: 0}
    default : throw new Error("Acao invalida")
    }
}

function contador() {
    const[estado, dispatch] = useReducer(reducer, estadoInicial)

    return (
        <div>
            <p>Contador {estado.contador}</p>
            <button onClick={() => dispatch({tipo: "incrementar"})}>incrementar</button>
            <button onClick={() => dispatch({tipo: "decrementar"})}>decrementar</button>
            <button onClick={() => dispatch({tipo: "resetar"})}>resetar</button>
        </div>
    )
}

export default contador
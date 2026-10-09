import React, { useState, useEffect, use } from "react";

function useEffect() {
    const[contador, setContador] = useState(0);

    useEffect(() => {
        document.title = `Clicou ${contador} vezes`;
    });

    return (
        <div>
            <p>Voce clicou {contador} vezes</p>
            <button onClick={() => setContador(contador + 1)}>Clique aqui</button>
        </div>
    )
}

export default useEffect;
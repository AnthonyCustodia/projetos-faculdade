import { createContext, useState } from "react";

export const MeuContexto = createContext();

export const MeuContextoProvider = ({children}) => {
    const [message, setMessage] = useState("Primeira mensagem");
    const valorContexto = {
        message, 
        setMessage,
    }

    return(
        <MeuContexto.Provider value={valorContexto}>
            {children}
        </MeuContexto.Provider>
    )
};
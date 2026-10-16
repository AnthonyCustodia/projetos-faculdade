import { useContext, useState } from "react";
import { MeuContexto } from "../contexts/MeuContexto";

const ValorContexto = () => {
    const {message} = useContext(MeuContexto);

    return(
        <div>
            Valor do contexto é: {message}
        </div>
    )
}

export default ValorContexto;
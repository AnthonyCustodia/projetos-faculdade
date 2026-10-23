import { useState } from "react";
import { MeuContextoProvider } from "./contexts/MeuContexto";
import ValorContexto from "./components/ValorContexto";
import ComponenteFilho from "./components/ComponenteFilho";
import Contador from "./components/Contador";


function App() {
  const[count, setCount] = useState(0)


  return(
    <>
    <MeuContextoProvider>
      <ComponenteFilho></ComponenteFilho>
      <ValorContexto></ValorContexto>
    </MeuContextoProvider>
    <Contador></Contador>\
    </>
  )
}

export default App
import Welcome from './components/Welcome'
import BomDia from './components/BomDia'
import Pai from './components/Pai'
import Descricao from './components/Descricao'
import Cachorro from './components/Cachorro'
import Contador from './components/Contador'

function App() {
  return (
    <>
      <Welcome />
      <BomDia />
      <Pai />
      <Contador />
      <Descricao nome="Pedro" idade={36} />
      <Cachorro nome="Duque" raca="SRD" />
    </>
  )
}

export default App


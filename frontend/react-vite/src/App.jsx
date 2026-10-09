import { useEffect } from 'react'

// Aula 07
import Welcome from './components/Welcome'
import BomDia from './components/BomDia'
import Pai from './components/Pai'
import Descricao from './components/Descricao'
import Cachorro from './components/Cachorro'
import Counter from './components/Counter'
import UserInfoForm from './components/UserInfoForm'
import MeuComponente from './components/MeuComponente'

// Aula 08
import Button from './components/Button'
import PaiFunction from './components/PaiFunction'
import Form from './components/Form'
import RenderConditional from './components/RenderConditional'
import LoginButton from './components/LoginButton'
import MsgAlerta from './components/MsgAlerta'
import NumberList from './components/NumberList'
import BotaoEstilizado from './components/BotaoEstilizado'
import BotaoAzul from './components/BotaoAzul'
import Exercises from './components/Exercises'

import { demoMetodosArray } from './utils/metodosArray'
import { verificaIdade, compararIdade, compararIdadeTernario } from './utils/ternario'

function App() {
  // Mostra no console os exemplos de JS puro vistos na aula 08
  useEffect(() => {
    demoMetodosArray()
    console.log(verificaIdade(5, true))
    console.log(compararIdade(30, 30))
    console.log(compararIdadeTernario(30, 30))
  }, [])

  return (
    <div>
      <h1>Aulas 07 e 08 - ReactJS</h1>

      <h2>Aula 07</h2>

      <section>
        <h3>Componente simples</h3>
        <Welcome />
      </section>

      <section>
        <h3>Variáveis no JSX</h3>
        <BomDia />
      </section>

      <section>
        <h3>Hierarquia (Pai e Filho)</h3>
        <Pai />
      </section>

      <section>
        <h3>Props</h3>
        {/* 2.3 Props */}
        <Descricao nome="Jorge" idade={46} />
      </section>

      <section>
        <h3>Desestruturação de props</h3>
        <Cachorro nome="Rex" raca="Labrador" />
      </section>

      <section>
        <h3>useState</h3>
        <MeuComponente />
        <Counter />
      </section>

      <section>
        <h3>Vários useState (formulário)</h3>
        <UserInfoForm />
      </section>

      <h2>Aula 08</h2>

      <section>
        <h3>Eventos</h3>
        <Button />
      </section>

      <section>
        <h3>Função do pai executada pelo filho</h3>
        <PaiFunction />
      </section>

      <section>
        <h3>Evento de formulário</h3>
        <Form />
      </section>

      <section>
        <h3>Renderização condicional</h3>
        <RenderConditional user="Jorge" />
        <RenderConditional />
        <LoginButton loggedIn={true} />
        <LoginButton loggedIn={false} />
        <MsgAlerta mensagem="Preencha todos os campos" />
        <MsgAlerta />
      </section>

      <section>
        <h3>Listas e chaves</h3>
        {/* 2.13 Listas e Chaves */}
        <NumberList numbers={['teste', 'e', 1, 2, 3, 4, 5]} />
      </section>

      <section>
        <h3>Estilização</h3>
        <BotaoEstilizado />
        <BotaoAzul />
      </section>

      <section>
        <h3>Exercícios</h3>
        <Exercises />
      </section>
    </div>
  )
}

export default App

import { useState } from 'react'

// Exercício 1: Componente de Saudação
const Greeting = ({ name }) => {
  return <h1>Olá, {name}!</h1>
}

// Exercício 2: Componente Contador
const Counter = () => {
  const [count, setCount] = useState(0)
  return (
    <div>
      <p>Contador = {count} .</p>
      <button onClick={() => setCount(count + 1)}> + </button>
      <button onClick={() => setCount(count - 1)}> - </button>
    </div>
  )
}

// Exercício 3: Lista de Tarefas
const TaskList = ({ tasks }) => {
  if (tasks.length === 0) {
    return <p>Não há tarefas a mostrar.</p>
  }
  return (
    <ol>
      {tasks.map((task) => (
        <li key={task.id}>{task.text}</li>
      ))}
    </ol>
  )
}

// Componente Exercises que agrupa todos os exercícios
const Exercises = () => {
  // Dados de exemplo para o componente TaskList
  const tasks = [
    { id: 1, text: 'Ir no mercado' },
    { id: 2, text: 'Estudar React em casa também' },
    { id: 3, text: 'Ir embora mais cedo'}
  ]

  return (
    <div>
      <h2>Exercício 1: Saudação</h2>
      <Greeting name="João" />

      <h2>Exercício 2: Contador</h2>
      <Counter />

      <h2>Exercício 3: Lista de Tarefa</h2>
      <TaskList tasks={tasks} />
      <TaskList tasks={[]} />
    </div>
  )
}

export default Exercises

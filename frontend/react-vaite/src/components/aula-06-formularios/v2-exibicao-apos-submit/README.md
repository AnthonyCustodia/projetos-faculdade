# Aula 06 — Formulários (versão 2: exibição após o envio)

Componente: [`UserInfoForm2.jsx`](./UserInfoForm2.jsx)

## O que esta versão ensina

Separar **o que o usuário está digitando** de **o que foi efetivamente
enviado**. Para isso, usamos um segundo estado.

Compare com a [versão 1](../v1-exibicao-imediata/README.md): os campos são
iguais, muda só o momento em que os dados aparecem na tela.

| | Versão 1 | Versão 2 |
| --- | --- | --- |
| Os dados aparecem | a cada tecla | só depois do envio |
| Estados usados | 4 (um por campo) | 5 (4 campos + 1 de confirmação) |
| `handleSubmit` | loga no console | grava o estado de confirmação |

## A diferença em uma linha

Na versão 1, o texto exibido lê o estado dos campos direto:

```jsx
<p>Meu nome é: {nome}</p>
```

Na versão 2, o estado dos campos é usado para **montar** o formulário, e a
exibição lê um estado separado:

```jsx
const [dadosEnviados, setDadosEnviados] = useState(null)   // null = ainda não

const handleSubmit = (event) => {
  event.preventDefault()
  setDadosEnviados({ nome, email, telefone, cep })          // guarda um retrato
}

// ...
{dadosEnviados && (
  <p>Meu nome é: {dadosEnviados.nome}</p>
)}
```

## Por que guardar um objeto

Guardar `{ nome, email, ... }` em vez de reaproveitar os estados soltos cria
um **retrato** do momento do envio. Se o usuário voltar a digitar depois de
enviar, a confirmação continua mostrando o que foi enviado de verdade, e não
o que está no campo no momento.

Em uma tela de cadastro real é exatamente esse o comportamento esperado: você
envia, vê a confirmação, e o formulário ainda está ali para ser corrigido.

## O `{dadosEnviados && (...)}`

É o `&&` da Aula 05, aplicado a um objeto. Funciona bem porque
`dadosEnviados` começa como `null`:

- `null && (...)` → devolve `null` → React não desenha nada
- `{...} && (...)` → devolve o JSX → aparece a confirmação

Aqui não existe o problema do `0` descrito na Aula 05, porque o valor nunca é
um número.

## Onde esse código realmente vai parar

O `event.preventDefault()` cancela o envio nativo, então o formulário não
chega a lugar nenhum ainda. Num projeto real, o `handleSubmit` faria uma
chamada de rede e só confirmaria na tela depois da resposta:

```jsx
const handleSubmit = async (event) => {
  event.preventDefault()

  const resposta = await fetch('/api/usuarios', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(dadosEnviados),
  })

  if (resposta.ok) {
    setDadosEnviados(await resposta.json())
  } else {
    setErro('Não foi possível salvar. Tente novamente.')
  }
}
```

Repare que `setDadosEnviados` passa a ser chamado **depois** da resposta, e
não antes. É essa a diferença entre uma tela que confirma o que o servidor
aceitou e uma que confirma o que o usuário digitou.

## Exercícios

1. Mova a chamada para o `fetch` acima e trate o erro. Use o `estado` de
   loading para desabilitar o botão enquanto a requisição está em andamento.
2. Zere o formulário depois do envio, com um único `setNome('')` para cada
   campo. Repare que a confirmação continua aparecendo — por quê?
3. Envie os dados para a API `clientes-api` que existe no repositório
   (`backend/clientes-api`).

---

Voltar ao [mapa das aulas](../../../../README.md#mapa-das-aulas)

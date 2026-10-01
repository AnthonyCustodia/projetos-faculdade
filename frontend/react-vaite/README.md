# Aulas de React

Projeto de estudo em React, com cada conceito isolado em um componente
pequeno e independente. A ideia é que você possa abrir **um arquivo por vez**,
entender o que ele faz, e ver o resultado na tela imediatamente.

Cada pasta dentro de `src/components/` corresponde a uma aula e aparece
como um bloco separado na aplicação, com o caminho do arquivo no cabeçalho.

---

## Como rodar

```bash
npm install     # instala as dependências
npm run dev     # sobe o servidor em http://localhost:5173
```

| Comando           | O que faz                                        |
| ----------------- | ------------------------------------------------ |
| `npm run dev`     | Servidor de desenvolvimento, com recarga automática |
| `npm run build`   | Gera a versão de produção na pasta `dist/`        |
| `npm run preview` | Serve a build de produção para conferir o resultado |
| `npm run lint`    | Roda o ESLint                                     |

> Rode `npm run lint` antes de dar commit. Ele é rápido e pega os erros mais
> comuns: variável declarada e não usada, import com o caminho errado, `prop`
> escrita com a letra trocada.

---

## Mapa das aulas

| Aula | Pasta | Conceito | Componentes |
| --- | --- | --- | --- |
| 01 | `aula-01-componentes/` | O que é um componente, interpolação no JSX | `Welcome`, `BomDia` |
| 02 | `aula-02-props/` | Passar dados pela tag, valores padrão | `Descricao`, `Cachorro` |
| 03 | `aula-03-composicao/` | Componente dentro de componente | `Pai`, `Filho` |
| 04 | `aula-04-estado/` | `useState` e redesenho da tela | `Contador` |
| 05 | `aula-05-render-condicional/` | `&&` e operador ternário | `RenderConditional`, `VerificarIdade` |
| 06 | `aula-06-formularios/` | Inputs controlados e `onSubmit` | `UserInfoForm`, `UserInfoForm2` |

---

## Os conceitos, em resumo

### Componente é uma função que devolve JSX

```jsx
const Welcome = () => <h2>Olá, mundo!</h2>
```

O nome precisa começar com **maiúscula**. No JSX, tag minúscula é HTML
nativo (`<div>`) e tag maiúscula é um componente seu (`<Welcome />`).
Errar a maiúscula não dá erro: o React renderiza uma tag vazia e você
fica procurando o problema no lugar errado.

### Props são os parâmetros do componente

```jsx
<Descricao nome="Pedro" idade={36} />   // no pai
const Descricao = ({ nome, idade = 0 }) => ...   // no filho
```

Texto vai entre aspas, expressão JavaScript vai entre chaves. `idade="36"`
chega como texto; `idade={36}` chega como número — e isso muda o resultado
de comparações e cálculos.

Cada prop é procurada pelo **nome exato** escrito na tag. Não há
correspondência por posição nem por semelhança de nome.

### Não existe herança: existe composição

Um componente é usado dentro do outro como se fosse uma tag.
Para componentes "invólucro" (card, modal, layout), existe a prop
`children`, que deixa o **pai** decidir o que vai dentro.

### `useState` guarda um valor e avisa o React

```jsx
const [count, setCount] = useState(0)
```

Variável comum não redesenha a tela. `useState` avisa.

Quando o novo valor depender do anterior, passe uma **função**:

```jsx
setCount((c) => c + 1)        // certo: o React aplica na ordem
setCount(count + 1)            // funciona, mas depende do valor atual
```

### Renderização condicional

```jsx
{condicao && <JSX />}                        // atalho, cuidado com o 0
{condicao ? <JSX /> : null}                  // seguro
```

### Input controlado

Todo `<input>` controlado precisa de três peças que andam juntas:

```jsx
<input value={nome} onChange={(e) => setNome(e.target.value)} />
//      ^                ^                                  ^
//  o que aparece   como entra no estado            onde o valor mora
```

O estado do React manda no campo, e não o contrário.

---

## Exercícios que quebram de propósito

Estes foram bugs reais deste projeto, corrigidos aqui. Vale desfazer cada
um e observar o que muda:

1. **Import com a letra errada.** `import Contador from './components/contador'`
   — em minúsculas. No Windows o sistema de arquivos ignora isso e o projeto
   funciona; no Linux e no deploy, o build quebra. Nomes de arquivo precisam
   bater exatamente com o import.

2. **Prop com o nome errado.** `RenderConditional` espera `user`, mas o pai
   passava `nome="Kaique"`. O componente recebeu `undefined` e não renderizou
   nada — sem aviso no console.

3. **`useState(null)` em input controlado.** Faz o React avisar
   *"changing an uncontrolled input to be controlled"*. O valor inicial
   correto para um campo de texto é `''`.

4. **`&&` com o valor `0`.** Passe `user={0}` para o `RenderConditional` e
   o zero aparece na tela. O `&&` devolve o segundo operando, e `0` é um
   valor que o React renderiza. Use o ternário com `: null`.

5. **`handleSubmit` sem `event.preventDefault()`.** O formulário recarrega a
   página ao enviar, porque esse é o comportamento nativo do HTML.

6. **`import React from 'react'` desnecessário.** Com o *automatic JSX
   runtime* (padrão do Vite), o import só gerava aviso de variável não usada.

---

## Convenções adotadas

Para o código ficar uniforme e fácil de comparar entre aulas:

- Componentes declarados com `const` seta (`const X = () => ...`).
- **2 espaços** de indentação, sem ponto e vírgula (padrão do Vite).
- Nomes de arquivo idênticos ao nome do componente.
- Import removido quando não é usado.
- Um componente por arquivo, com o cabeçalho em comentário explicando a
  aula e o motivo das decisões.
- Componentes organizados por aula, não por tipo.

---

## Próximos passos

Sugestões de exercícios:

1. **`useEffect`.** Troque o `console.log` da versão 1 dos formulários por
   um `useEffect` que dispara quando `nome` muda. Ele é para **efeitos
   colaterais** (chamadas de API, timers, logging), nunca para derivar estado
   que você já tem em mãos.
2. **Formulário com validação.** Mostre a mensagem de erro embaixo do campo
   que estiver inválido, e desabilite o botão de envio.
3. **`children`.** Crie um componente `<Cartao>` que só desenha a moldura, e
   passe o conteúdo por dentro dele.
4. **Estado elevado.** Mova o estado do contador para o `App` e passe
   `count`/`setCount` por props, para ver o "lifting state up".
5. **Lista com `.map()`.** Renderize os cachorros a partir de um array, com
   `key` em cada item.
6. **TypeScript.** Troque a extensão dos arquivos para `.tsx` e declare as
   props com uma `interface`.

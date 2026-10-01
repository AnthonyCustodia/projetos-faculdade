# Aula 06 — Formulários (versão 1: exibição imediata)

Componente: [`UserInfoForm.jsx`](./UserInfoForm.jsx)

## O que esta versão ensina

O **input controlado**: o estado do React é a fonte da verdade do campo de
texto, e o que aparece na tela é o resultado desse estado.

As duas versões desta aula estão no mesmo estado final do formulário, mas
diferem em **quando** os dados são mostrados. Compare com a
[versão 2](../v2-exibicao-apos-submit/README.md).

## O que acontece a cada tecla

1. O usuário digita um caractere.
2. O navegador dispara o `onChange` com o evento.
3. `e.target` é o próprio `<input>`, e `e.target.value` é o texto novo.
4. `setNome(...)` grava o valor e o React agenda um redesenho.
5. O componente roda de novo, e `value={nome}` devolve o texto ao campo.

O ponto 5 é o que diferencia React do JavaScript de um formulário comum: o
valor não vai "do DOM para a variável", e sim do evento para o estado e de
volta para o DOM. O campo nunca tem a fonte da verdade em si mesmo.

## As três peças que precisam andar juntas

```jsx
const [nome, setNome] = useState('')      // 1. onde o valor fica
<input
  value={nome}                            // 2. o que aparece no campo
  onChange={(e) => setNome(e.target.value)} // 3. como o valor entra
/>
```

Esqueça a peça 2 e o campo vira "não controlado" e para de acompanhar o
estado. Esqueça a peça 3 e não dá para digitar.

## Dois detalhes que costumam dar problema

**Valor inicial é `''`, não `null`.** Começar em `null` faz o React avisar no
console que o input está passando de não controlado para controlado, e o
comportamento fica inconsistente. Para campo de texto, string vazia.

**`event.preventDefault()` no `handleSubmit`.** Sem ele, o navegador recarrega
a página ao enviar o formulário. Esse é o comportamento nativo do HTML; em
React queremos tratar o envio no JavaScript.

## Exercícios

1. Troque `type="email"` por `type="text"` e veja o que muda na validação
   nativa do navegador.
2. Adicione um campo "Mensagem" seguindo o mesmo padrão dos outros quatro.
3. Troque o `console.log` por um `alert`.
4. Crie um estado só, `const [dados, setDados] = useState({})`, e guarde
   todos os campos dentro dele. Compare a quantidade de `useState`.

---

Voltar ao [mapa das aulas](../../../../README.md#mapa-das-aulas)

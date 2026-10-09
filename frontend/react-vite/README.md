# Aula 01 – React (Aulas 07 e 08)

Projeto com os componentes dos slides do Prof. Jorge Henrique (Senac – Desenvolvimento Front-End).

## Como rodar
```bash
npm install
npm run dev
```
Abra o endereço que o terminal mostrar (normalmente http://localhost:5173).

## Como criar do zero (como na aula)
```bash
mkdir aula01 && cd aula01
npx create-vite .      # React -> JavaScript
npm install
npm run dev
```
Depois copie a pasta `src/components` deste projeto e o `App.jsx`.

## Dicas de VS Code (Aula 07)
- Extensão: **ES7 + React/Redux/React-Native snippets** (use `rfce` / `rafce` + Enter).
- Emmet: Settings → Extensions → Emmet → *Include Languages* → Add Item: `javascript` → `javascriptreact`.

## Estrutura
- `src/components/` Aula 07: Welcome, BomDia, Pai, Filho, Descricao, Cachorro, Counter, MeuComponente, UserInfoForm
- `src/components/` Aula 08: Button, PaiFunction, FilhoFunction, Form, RenderConditional, LoginButton, MsgAlerta, NumberList, BotaoEstilizado, BotaoAzul, Exercises
- `src/utils/` JS puro: operador ternário e métodos de array (veja o console do navegador)
- `src/index.css` estilos globais (classe `.botao-azul`)

## Lição de casa (Aula 08)
Pesquisar como estilizar com **CSS Modules** (`Componente.module.css`).

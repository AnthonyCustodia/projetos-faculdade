# estoque-app

Frontend do trabalho **Aplicação de controle de estoque** (Programador WEB — Senac).
React + Vite + React Router + Axios. Consome a [estoque-api](../estoque-api).

## Como rodar

1. Suba a `estoque-api` primeiro (veja o README dela). Ela deve estar em `http://localhost:8080`.
2. Instale as dependências e rode o front:
   ```bash
   npm install
   npm run dev
   ```
3. Abra `http://localhost:5173`.

Se a API estiver em outro endereço, copie `.env.example` para `.env` e altere `VITE_API_URL`.

No primeiro acesso, use **Cadastrar operador** na tela de login para criar um usuário.

## Telas

| Rota | Tela |
|---|---|
| `/login` | Login com e-mail e senha |
| `/cadastro` | Cadastro de operador |
| `/produtos` | **Tela principal**: tabela de produtos (id, código de barras, nome, descrição, estoque, status), com busca e filtro por status |
| `/produtos/novo` | Cadastro de produto |
| `/produtos/:id/editar` | Edição de produto |
| `/produtos/:id/movimentacoes` | Estoque atual, nova movimentação (entrada/saída) e tabela com as movimentações **desse** produto |

Todas as rotas, exceto login e cadastro, exigem login (`RotaPrivada`).

## Como funciona o login (JWT)

- O backend valida e-mail e senha e devolve um token JWT.
- O front guarda o token no `localStorage` (`AuthContext.jsx`).
- `services/api.js` tem dois interceptors do Axios:
  - **requisição**: coloca `Authorization: Bearer <token>` em toda chamada;
  - **resposta**: se a API responder **401** (token ausente, inválido ou expirado), apaga o token
    e volta para o login com o aviso "Sua sessão expirou".
- O botão **Sair** apaga o token.

## Regras refletidas na interface

- Não existe botão de excluir produto, só **Desativar/Reativar**.
- O formulário de produto não tem campo de estoque: ele só muda por movimentações.
- A tabela de movimentações não tem ações de editar/excluir.
- Antes de registrar uma movimentação aparece uma confirmação, porque ela não pode ser desfeita.
- Produto desativado não pode ser movimentado; saída maior que o estoque é bloqueada
  (no navegador e também pela API).

## Estrutura

```
src/
  services/api.js          instância do Axios + interceptors + mensagens de erro
  contexts/AuthContext.jsx login/logout e usuário logado
  contexts/useAuth.js      hook para acessar o contexto
  components/              Layout (cabeçalho), RotaPrivada, Alerta
  pages/                   Login, CadastroOperador, Produtos, ProdutoForm, Movimentacoes, NaoEncontrado
```

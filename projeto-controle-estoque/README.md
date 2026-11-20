# Aplicação de controle de estoque

Trabalho em grupo de Programador WEB (Senac): cadastro de produtos, estoque disponível e
entradas/saídas de produtos. Enunciado em [trabalho_controle_estoque.pptx.pdf](trabalho_controle_estoque.pptx.pdf).

| Pasta | O que é |
|---|---|
| [estoque-api](estoque-api) | Backend — Spring Boot, Spring Security + JWT, PostgreSQL |
| [estoque-app](estoque-app) | Frontend — React, Vite, React Router, Axios |

## Rodando tudo

```bash
# 1. Backend (precisa do banco "estoque-db" criado no PostgreSQL)
cd estoque-api
./mvnw spring-boot:run

# 2. Frontend (em outro terminal)
cd estoque-app
npm install
npm run dev
```

Acesse `http://localhost:5173`, cadastre um operador e faça login.

## Critérios de avaliação

| Critério | Onde está |
|---|---|
| Controle de acesso (JWT) | `estoque-api/.../config` e `jwt` · `estoque-app/src/services/api.js` e `contexts` |
| CRUD das entidades | Produtos: criar, listar, editar, desativar (sem exclusão, por regra). Movimentações: criar e listar (sem alteração/exclusão, por regra) |
| Relacionamentos | `Movimentacoes` → `Produtos` e `Movimentacoes` → `Usuarios` (`@ManyToOne`) |
| Banco de dados | PostgreSQL, tabelas `usuarios`, `produtos` e `movimentacoes` |

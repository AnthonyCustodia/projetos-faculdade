# estoque-api

Backend do trabalho **Aplicação de controle de estoque** (Programador WEB — Senac).
Spring Boot 4 + Spring Security + JWT + PostgreSQL.

## Como rodar

1. Crie o banco no PostgreSQL:
   ```sql
   CREATE DATABASE "estoque-db";
   ```
2. Usuário/senha padrão: `postgres` / `Sen@c2023`. Se o seu PostgreSQL usa outra senha, defina antes de rodar:
   ```bash
   # PowerShell
   $env:DB_PASSWORD="minha-senha"
   # Git Bash
   export DB_PASSWORD="minha-senha"
   ```
   (também existem `DB_USERNAME` e `DB_URL`).
3. Suba a API (precisa de JDK 17 ou mais novo):
   ```bash
   ./mvnw spring-boot:run
   ```
   A API fica em `http://localhost:8080`. As tabelas são criadas sozinhas (`ddl-auto: update`).

O frontend (React/Vite em `http://localhost:5173`) já está liberado no CORS.
Para outra porta, altere `estoque.cors.origens-permitidas` no `application.yaml`.

## Testes

```bash
./mvnw test
```
Os testes usam banco H2 em memória (não precisam do PostgreSQL) e cobrem login/JWT, CORS,
cadastro/edição/desativação de produtos e as regras de entrada e saída de estoque.

## Endpoints

Tudo, exceto login e cadastro de usuário, exige o header `Authorization: Bearer <token>`.
Sem token válido a API responde **401**. Erros vêm como `{ "mensagem": "..." }`.

| Método | URL | O que faz |
|---|---|---|
| POST | `/usuarios/cadastro` | Cria operador `{ nome, email, senha }` (público) |
| POST | `/usuarios/login` | `{ email, senha }` → `{ token, nome, email }` (público) |
| GET | `/produtos` | Lista todos os produtos (ativos e inativos) |
| GET | `/produtos/{id}` | Um produto |
| POST | `/produtos` | Cria `{ codigoBarras, nome, descricao }` — estoque começa em 0 |
| PUT | `/produtos/{id}` | Edita `{ codigoBarras, nome, descricao }` — não mexe no estoque |
| PATCH | `/produtos/{id}/desativar` | Desativa (não existe DELETE) |
| PATCH | `/produtos/{id}/ativar` | Reativa |
| GET | `/produtos/{id}/movimentacoes` | Movimentações **desse** produto (mais recente primeiro) |
| POST | `/produtos/{id}/movimentacoes` | `{ tipo: "ENTRADA" \| "SAIDA", quantidade }` |

### Regras implementadas

- O estoque **só** muda por movimentação: o cadastro e a edição de produto ignoram o campo `estoque`.
- Produto não é deletado, apenas desativado. Produto desativado não pode ser movimentado.
- Movimentação não pode ser alterada nem excluída (não há PUT/DELETE; colunas `updatable = false`).
- Não existe endpoint que lista as movimentações de todos os produtos.
- **ENTRADA** soma a quantidade ao estoque; **SAIDA** subtrai. A movimentação e a atualização
  do estoque são gravadas na mesma transação (`@Transactional`).
- Quantidade deve ser maior que zero; saída maior que o estoque disponível é recusada
  (`Estoque insuficiente!`).
- Cada movimentação guarda o usuário que a registrou (pego do token).

## Banco de dados

```
usuarios (id, nome, email UNIQUE, senha[bcrypt])
produtos (id, codigo_barras UNIQUE, nome, descricao, estoque, ativo)
movimentacoes (id, tipo, quantidade, data_hora,
               produto_id -> produtos.id, usuario_id -> usuarios.id)
```

## Exemplo rápido (curl)

```bash
curl -X POST localhost:8080/usuarios/cadastro -H "Content-Type: application/json" \
  -d '{"nome":"Operador","email":"operador@senac.br","senha":"123456"}'

curl -X POST localhost:8080/usuarios/login -H "Content-Type: application/json" \
  -d '{"email":"operador@senac.br","senha":"123456"}'
# copie o "token" da resposta

curl -X POST localhost:8080/produtos -H "Authorization: Bearer SEU_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"codigoBarras":"7891234567890","nome":"Caneta","descricao":"Caneta azul"}'

curl -X POST localhost:8080/produtos/1/movimentacoes -H "Authorization: Bearer SEU_TOKEN" \
  -H "Content-Type: application/json" -d '{"tipo":"ENTRADA","quantidade":10}'
```

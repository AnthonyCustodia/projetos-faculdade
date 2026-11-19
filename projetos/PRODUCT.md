# Product

<!-- impeccable:product-schema 1 -->

## Platform

web

## Stack

React 19 + Vite + react-router-dom no frontend; Spring Boot + Spring Security + JWT no backend, com banco de dados relacional. É a mesma stack das aulas (referências: `frontend/react-router-app`, `backend/pedido-api`).

## Users

- **Operador de estoque:** funcionário de um almoxarifado ou loja pequena que, ao longo do dia, consulta produtos e registra entradas e saídas, possivelmente com leitor de código de barras. Precisa de velocidade, clareza e confiança de que o saldo exibido está correto.
- **Professor e banca avaliadora:** avaliam o trabalho em grupo (3 pessoas) na apresentação. Precisam ver de forma clara os critérios cobrados: controle de acesso (JWT), CRUD das entidades, relacionamentos entre entidades e persistência em banco de dados.

O produto é projetado para o operador real, e a apresentação deve deixar cada critério de avaliação visível.

## Product Purpose

Aplicação web para controlar o cadastro de produtos, o estoque disponível e as entradas e saídas de produtos. O sucesso tem dois lados: o operador consegue registrar movimentações e confiar no saldo, e o grupo demonstra todos os critérios de avaliação funcionando de ponta a ponta.

## Positioning

O estoque é um registro auditável: o saldo de um produto nunca é editado diretamente, ele só muda por movimentações, e as movimentações são imutáveis (não podem ser alteradas nem excluídas). Produtos também nunca são apagados, apenas desativados. A confiança no número vem do histórico preservado.

## Operating Context

- Ao acessar o site, o operador precisa fazer login com e-mail e senha. O backend controla o acesso e o frontend guarda o token JWT.
- A tela principal mostra os produtos cadastrados em uma tabela.
- A tela de controle de estoque lista as movimentações de **um produto específico**, nunca todas as movimentações de todos os produtos de uma vez.
- Entrega: código do frontend e do backend no git, mais uma apresentação feita pelo grupo.

## Capabilities and Constraints

- **Login:** e-mail e senha, com autenticação JWT feita pelo backend e token guardado no frontend.
- **Produtos:** a tabela mostra, no mínimo, id, código de barras, nome, descrição e estoque. É possível editar o produto. Exclusão é proibida; existe apenas desativação.
- **Movimentações:** os tipos são `Entrada` e `Saída`. Uma entrada registra a movimentação e soma a quantidade ao estoque; uma saída registra a movimentação e subtrai a quantidade. Movimentações não podem ser editadas nem excluídas. A tabela mostra, no mínimo, id da movimentação, tipo e quantidade movimentada.
- **Regra central:** o estoque só muda por meio de movimentações.
- **Em aberto (não especificado no enunciado):** se uma saída pode deixar o estoque negativo; se produtos desativados aparecem na listagem e se aceitam movimentações; se há cadastro de operadores ou apenas usuários criados previamente; se a movimentação guarda data/hora e o operador responsável.

## Brand Commitments

Nenhuma identidade definida. Ainda não há nome do sistema, empresa fictícia nem uso obrigatório de marca.

## Evidence on Hand

- Enunciado do trabalho: `trabalho_controle_estoque.pptx.pdf` (Prof. Roberto Fermino Medeiros, disciplina Programador WEB, Senac).
- Não há dados reais, clientes, depoimentos nem métricas. Dados de demonstração devem ser claramente fictícios.

## Product Principles

1. **O saldo é derivado, nunca digitado.** Toda mudança de estoque passa por uma movimentação registrada.
2. **Nada se apaga.** Produtos são desativados e movimentações são permanentes; a interface deve deixar isso claro antes de cada ação irreversível.
3. **Rapidez para o operador.** As tarefas frequentes (achar um produto, registrar entrada ou saída) precisam de poucos passos e de retorno imediato.
4. **Critérios visíveis.** Login, CRUD, relacionamento produto → movimentações e persistência devem ser fáceis de demonstrar na apresentação.

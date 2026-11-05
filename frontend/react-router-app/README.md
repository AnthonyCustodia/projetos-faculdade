# Aula 10 - React Router

Projeto da aula de Desenvolvimento Front-End (Senac) sobre **React Router**, criado com Vite.

## Como rodar

```bash
npm install
npm run dev
```

## Conceitos praticados

| Conceito | Onde |
| --- | --- |
| `BrowserRouter` | `src/main.jsx` |
| `Routes` / `Route` | `src/App.jsx` |
| `Link` (navegação sem recarregar a página) | menu em `src/App.jsx` |
| `NavLink` + classe `active-link` | link "Contato" em `src/App.jsx` e `src/index.css` |
| `useNavigate` | `src/components/Login.jsx` |
| Rotas aninhadas + `Outlet` | `src/components/Dashboard.jsx` (`/dashboard/profile`, `/dashboard/settings`) |
| Rotas dinâmicas + `useParams` | `src/components/TaskDetails.jsx` (`/tasks/:taskId`) |
| Página 404 (`path="*"`) | `src/components/NotFound.jsx` (ex.: `/items/1`) |

## Estrutura

```
src/
├── components/   # páginas (Home, About, Contact, Login, Dashboard, Profile, Settings, TaskDetails, NotFound)
├── context/      # pasta pedida na aula (vazia por enquanto)
├── hooks/        # pasta pedida na aula (vazia por enquanto)
├── App.jsx       # menu e definição das rotas
├── main.jsx      # BrowserRouter
└── index.css
```

// Renderização condicional com && : só mostra o h1 se "user" for verdadeiro
function RenderConditional({ user }) {
  // se houver um usuario logado ele mostra uma mensagem
  return <div>{user && <h1>Bem Vindo : {user}!</h1>}</div>
}

export default RenderConditional

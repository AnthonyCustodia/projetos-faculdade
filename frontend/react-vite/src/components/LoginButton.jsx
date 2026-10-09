// Operador ternário: mostra "Sair" se estiver logado, senão "Entrar"
function LoginButton({ loggedIn }) {
  // Entrar -> logar
  // Sair -> deslogar
  return <div>{loggedIn ? <button>Sair</button> : <button>Entrar</button>}</div>
}

export default LoginButton

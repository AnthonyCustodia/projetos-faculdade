// Métodos de array usados no React: map, filter, reduce, sort, find, findIndex
export function demoMetodosArray() {
  // map: transforma cada elemento em outro valor
  const nomes = ['João', 'Maria', 'Pedro']
  const nomesMaiusculos = nomes.map((nome) => nome.toUpperCase())
  console.log('map:', nomesMaiusculos)

  const usuarios = [
    { nome: 'João', idade: 25 },
    { nome: 'Maria', idade: 17 },
    { nome: 'Pedro', idade: 30 },
  ]

  // filter: novo array só com quem atende à condição
  const usuariosMaiores = usuarios.filter((usuario) => usuario.idade >= 18)
  console.log('filter:', usuariosMaiores)

  // reduce: combina todos os elementos em um único valor
  const somaIdades = usuarios.reduce((total, usuario) => total + usuario.idade, 0)
  console.log('reduce:', somaIdades)

  // sort: ordena (cópia com [...] para não alterar o array original)
  const usuariosOrdenados = [...usuarios].sort((a, b) =>
    a.nome.localeCompare(b.nome)
  )
  console.log('sort:', usuariosOrdenados)

  // find: primeiro elemento que atende à condição
  const usuarioMaria = usuarios.find((usuario) => usuario.nome === 'Maria')
  console.log('find:', usuarioMaria)

  // findIndex: índice do primeiro elemento que atende à condição
  const indiceUsuarioMaior20 = usuarios.findIndex((usuario) => usuario.idade > 20)
  console.log('findIndex:', indiceUsuarioMaior20)
}

// Operador ternário: reduz um IF/ELSE a uma única linha
export function verificaIdade(idade, operadorTernario) {
  if (operadorTernario) {
    return idade >= 18 ? 'Operador Maior de idade' : 'Operador Menor de idade'
  } else {
    if (idade >= 18) {
      return 'Maior de Idade'
    } else {
      return 'Menor de idade'
    }
  }
}

export function compararIdade(idadePessoa1, idadePessoa2) {
  if (idadePessoa1 > idadePessoa2) {
    return 'A Pessoa 1 é mais velha.'
  } else if (idadePessoa1 < idadePessoa2) {
    return 'A Pessoa 2 é mais velha.'
  } else {
    return 'As pessoas têm a mesma idade.'
  }
}

export function compararIdadeTernario(idadePessoa1, idadePessoa2) {
  return idadePessoa1 > idadePessoa2
    ? 'A Pessoa 1 é mais velha.'
    : idadePessoa1 < idadePessoa2
    ? 'A Pessoa 2 é mais velha.'
    : 'As pessoas têm a mesma idade.'
}

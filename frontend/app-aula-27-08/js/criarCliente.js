function criarCliente(nome, email, telefone) {
    return {
        nome,
        email,
        telefone,
    };
}

const cliente = criarCliente("Joao Silva", "joaosilva@email.com", "(11) 99999-9999");

console.dir(cliente);
async function buscarDados(url) {
    const response = await fetch(url);
    const dados = await response.json();
    return dados;
}

(async () => {
    const dados = await buscarDados('https://jsonplaceholder.typicode.com/posts/1');
    console.log("Dados: ", dados);
})();

function esperar(ms) {
    return new Promise(resolve => {
        setTimeout(resolve, ms);
    });
}

async function minhaFUncao() {
    console.log("Iniciando minhaFUncao");
    await esperar(1000);
    console.log("Passaram-se 1 segudo");
    await esperar(2000);
    console.log("Passaram-se mais 2 segundos");

    return "Função concluida";
}

minhaFUncao()
    .then(resultado => {
        console.log(resultado);
    }) .catch(erro => {
        console.error("Ocorreu um erro", erro);
    });
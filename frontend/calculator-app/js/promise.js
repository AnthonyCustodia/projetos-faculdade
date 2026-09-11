function calcularSomaAssincrona(a, b) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            const resultado = a + b;
            if (isNaN(resultado)) {
                reject(new Error('Valores invalidos'));
            } else {
                resolve(resultado);
            }
        }, 2000);
    });
}

calcularSomaAssincrona(5, 3).then(resultado => console.log('Soma: ' + resultado)).catch(console.error("Erro: " + error));

async function minhaFuncaoAsync() {
    const resultado = await minhaOperacaoAssincrona();
    console.log("Resultado:" + resultado);
}
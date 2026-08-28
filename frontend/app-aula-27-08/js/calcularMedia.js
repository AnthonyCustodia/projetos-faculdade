function calcularMedia(numeros) {
    let soma = 0;
    for (const numero of numeros) {
        soma += numero;
    }

    console.count("Calcular media");
    return soma / numeros.length;
}

const numeros1 = [1, 2, 3, 4, 5];
const media1 = calcularMedia(numeros1);

console.log(`Media: ${media1}`);

const numeros2 = [6, 7, 8, 9, 10];
const media2 = calcularMedia(numeros2);

console.log(`Media: ${media2}`);
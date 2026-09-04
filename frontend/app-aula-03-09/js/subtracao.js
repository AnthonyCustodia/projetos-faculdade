function subtrair(a, b) {
    let menos = 0;
    menos = a-b;
    return menos;
}

const subtracao = subtrair(2, 1);
console.log(subtracao);

//-----------------------------------

const subtracaoV2 = (a, b) => a-b;
console.log(subtracaoV2(3, 4));
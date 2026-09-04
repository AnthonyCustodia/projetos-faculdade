function somar(a, b) {
    let soma = 0;
    soma = a+b;
    return soma;
}

const adicao = somar(2, 2);
console.log(adicao);

//----------------------------------

const adicaoV2 = (a, b) => a+b;
console.log(adicaoV2(3, 5));
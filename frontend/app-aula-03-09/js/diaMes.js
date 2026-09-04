function diaMes() {
    return new Date().getDate();
}

const dia = diaMes();
console.log(dia);

//----------------------------------------

const diaV2 = () => new Date().getDate();
console.log(diaV2());

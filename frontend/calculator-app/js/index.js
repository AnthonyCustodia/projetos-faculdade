let visor = document.getElementById("visor");

/*function adicionarNoVisor(value) {
    visor.value += value;
}

function calcular() {
    try {
        visor.value = eval(visor.value);
    } catch(error) {
        visor.value = "Erro";
    }
}

function limparVisor() {
    visor.value = "";
}*/

const adicionarNoVisor = (value) => visor.value += value;
const calcular = () => {
    try {
        visor.value = eval(visor.value);
    } catch(error) {
        visor.value = "Erro";
    }
}
const limparVisor = () => visor.value = "";
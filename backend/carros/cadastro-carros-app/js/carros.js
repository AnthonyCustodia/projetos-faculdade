let carroId = -1;

async function adicionarCarro() {
    const marca = document.getElementById('marca');
    const modeloInput = document.getElementById('modelo');

    const carro = {
        marca: marca.value,
        modelo: modeloInput.value,
    };

    if (carroId !== -1) {
        const response = await api.put("carros/atualizar/" + carroId, carro);
        if (response.status === 200) {
            alert(`Carro ${carroId} atualizado com sucesso`);
        }

    } else {
        const response = await api.post("carros/criar", carro);
        if (response.status === 200) {
            alert(`Carro ${response.data.id} criado com sucesso`);
        }
    }


    marcaSelect.value = '';
    modeloInput.value = '';

    carroId = -1;

    $('#cadastroCarroModal').modal('hide');

    carregarCarros();
}

async function carregarCarros() {
    const tabela = document.getElementById('tabelaCarros');
    const tbody = tabela.getElementsByTagName('tbody')[0];

    tbody.innerHTML = '';

    try {
        const response = await api.get("carros/listar");

        if (response.status === 200) {
            response?.data?.forEach((carro, index) => {
                const row = tbody.insertRow();

                const acoes = row.insertCell(0);
                const id = row.insertCell(1);
                const marca = row.insertCell(2);
                const modelo = row.insertCell(3);

                acoes.innerHTML = `
            <button class="btn btn-sm btn-outline-primary mr-2" onclick="editarCarro(${carro.id})">
                <i class="fas fa-edit"></i>
            </button>
            <button class="btn btn-sm btn-outline-danger" onclick="excluirCarro(${carro.id})">
                <i class="fas fa-trash"></i>
            </button>
        `;
                id.innerHTML = carro.id;
                marca.innerHTML = carro.marca;
                modelo.innerHTML = carro.modelo;
            });
        }
    } catch (e) {
        console.log(">>>", e);
        alert('Erro ao listar carros:', e);
    }

    // carrega as marcas no select
    atualizarSelectMarcas();
}

async function editarCarro(index) {

    try {
        const response = await api.get("carros/listar/" + index);
        if (response.status === 200) {
            carroId = index;

            document.getElementById('marca').value = response.data.marca;
            document.getElementById('modelo').value = response.data.modelo;

            $('#cadastroCarroModal').modal('show');
        } else {
            alert("Carro não encontrado!")
        }
    } catch (e) {
        alert('Erro ao listar carros:', e);
    }
}

async function excluirCarro(index) {
    if (confirm('Tem certeza que deseja excluir este carro?')) {
        try {
            const response = await api.delete("carros/deletar/" + index);
            if (response.status === 200) {
                console.log(`Carro ${index} deletado com sucesso`);
                carregarCarros();
            }
        } catch (error) {
            console.error(`Erro ao deletar carro ${index}:`, error);
        }
    }
}
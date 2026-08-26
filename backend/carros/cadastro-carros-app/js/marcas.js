async function atualizarSelectMarcas() {
    const select = document.getElementById('marca');
    if (!select) return;

    try {
        const resp = await api.get('/marcas/listar');
        const marcas = resp.data;

        select.innerHTML = '';
        const padrao = document.createElement('option');
        padrao.value = '';
        padrao.textContent = 'Selecione a marca';
        select.appendChild(padrao);

        if (marcas && marcas.length > 0) {
            marcas.forEach(m => {
                const opt = document.createElement('option');
                opt.value = m.id;
                opt.textContent = m.nome;
                select.appendChild(opt);
            });
            select.disabled = false;
        } 
    } catch (err) {
        console.error('Erro ao carregar marcas:', err);
    }
}

async function adicionarMarca() {
    const marcaInput = document.getElementById('input-marca');

    const marca = {
        nome: marcaInput.value,
    };

    const response = await api.post("marcas/criar", marca);
    if (response.status === 200) {
        alert(`Marca ${response.data.id} criada com sucesso`);
    }


    marcaInput.value = '';

    $('#cadastroMarcaModal').modal('hide');

    atualizarSelectMarcas();
}
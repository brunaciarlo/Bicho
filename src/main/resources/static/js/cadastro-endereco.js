const cep = document.querySelector('#cep');
const endereco = document.querySelector('#endereco');
const estado = document.querySelector('#estado');
const cidade = document.querySelector('#cidade');


cep.addEventListener('focusout', async () => {
	const cepNumeros = cep.value.replace("-", "");

    const response = await fetch(`https://viacep.com.br/ws/${cepNumeros}/json/`);

    if(!response.ok){
        throw await response.json();
    }

    const responseCep = await response.json();

    endereco.value = responseCep.logradouro;
    estado.value = responseCep.uf;
    cidade.value = responseCep.localidade;
})

function mascaraCEP(input) {
	let value = input.value.replace(/\D/g, "");

	if (value.length > 5) {
		  value = value.substring(0, 5) + "-" + value.substring(5, 8);
	}
	input.value = value;
}

    const form = document.getElementById('form-endereco');
    const botaoSalvar = document.getElementById('botao-salvar');
    
    const camposOriginais = Array.from(form.elements).reduce((dados, campo) => {
        if (campo.id) {
            dados[campo.id] = campo.value;
        }
        return dados;
    }, {});

    const verificarMudancas = () => {
        const alterado = Array.from(form.elements).some(campo => {
            if (campo.id && campo.value !== undefined) {
                return campo.value !== camposOriginais[campo.id];
            }
            return false;
        });
        botaoSalvar.disabled = !alterado;
    };

    Array.from(form.elements).forEach(campo => {
        if (campo.id) {
            campo.addEventListener('input', verificarMudancas);
        }
    });
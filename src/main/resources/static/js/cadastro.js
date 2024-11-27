const cep = document.querySelector('#cadastro-cep');
const endereco = document.querySelector('#cadastro-endereco');
const estado = document.querySelector('#cadastro-estado');
const cidade = document.querySelector('#cadastro-cidade');


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

function mascaraData(input) {
    let value = input.value.replace(/\D/g, "");
    if (value.length > 2) {
        value = value.substring(0, 2) + "/" + value.substring(2);
    }
    if (value.length > 5) {
        value = value.substring(0, 5) + "/" + value.substring(5, 10);
    }
    input.value = value;
}

function mascaraTelefone(input) {
    let value = input.value.replace(/\D/g, "");
    if (value.length > 2) {
        value = "(" + value.substring(0, 2) + ") " + value.substring(2);
    }
    if (value.length > 8) {
        value = value.substring(0, 10) + "-" + value.substring(10, 14);
    }
    input.value = value;
}

function mascaraCPF(input) {
	let value = input.value.replace(/\D/g, "");
	if (value.length > 3) {
	  value = value.substring(0, 3) + "." + value.substring(3);
	}
	if (value.length > 7) {
	  value = value.substring(0, 7) + "." + value.substring(7);
	}
	if (value.length > 11) {
		  value = value.substring(0, 11) + "-" + value.substring(11, 13);
		}
	input.value = value;
}

    const form = document.getElementById('form-dados');
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
let nomeServico1, valor1, tempoEntrega1;
let nomeServico2, valor2, tempoEntrega2;
const valorTotal = document.getElementById("valor-total");
const valorTexto = valorTotal.textContent.trim();
const valorFloat = parseFloat(valorTexto.replace("R$", "").replace(",", "."));

document.addEventListener("DOMContentLoaded", async () => {
	
	const cep = document.querySelector('#cep');
	const cepNumeros = cep.value.replace("-", "");
	const sedexPreco = document.getElementById("sedex-preco");
	const sedexDias = document.getElementById("sedex-dias");
	const jadlogPreco = document.getElementById("jadlog-preco");
	const jadlogDias = document.getElementById("jadlog-dias");
	
    const dadosFrete = {
        from: { postal_code: "91260000" },
        to: { postal_code: cepNumeros },
        products: [
            {
                weight: 1,
                width: 10,
                height: 10,
                length: 10,
                quantity: 1
            }
        ],
        services: "2,3",
    };

    try {
        const response = await fetch('/calcular', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dadosFrete)
        });

        if (response.ok) {
            const resultado = await response.json();
			
			const filtrarResultado = resultado.map(service => ({
				nomeServico: service.name,
				preco: service.price,
				tempoEntrega: service.custom_delivery_time
			}));
			
            console.log(resultado);
			
			filtrarResultado.forEach((item, index) => {
				if(index === 0){
					nomeServico1 = item.nomeServico;
					valor1 = item.preco;
					tempoEntrega1 = item.tempoEntrega;
				}
				if(index === 1){
					nomeServico2 = item.nomeServico;
					valor2 = item.preco;
					tempoEntrega2 = item.tempoEntrega;
				}
			});
			
			valor1 = valor1.replace(".", ",");
			valor2 = valor2.replace(".", ",");
			
			sedexPreco.innerHTML = `R$${valor1}`;
			sedexDias.innerHTML = ` - ${tempoEntrega1} dias úteis`;
			
			jadlogPreco.innerHTML = `R$${valor2}`;
			jadlogDias.innerHTML = ` - ${tempoEntrega2} dias úteis`;
			
			
        } else {
            console.error("Erro ao calcular frete:", await response.text());
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }
});

document.getElementById("jadlog").addEventListener("click", async () => {

	const freteTexto = valor2.trim();
	const freteFloat = parseFloat(freteTexto.replace(",", "."));
	
	let valorFinal = valorFloat + freteFloat;
	valorFinal = valorFinal.toFixed(2).replace(".", ",");
	
	valorTotal.innerHTML = `R$${valorFinal}`;	
		
});

document.getElementById("sedex").addEventListener("click", async () => {

	const freteTexto = valor1.trim();
	const freteFloat = parseFloat(freteTexto.replace(",", "."));
	
	let valorFinal = valorFloat + freteFloat;
	valorFinal = valorFinal.toFixed(2).replace(".", ",");
	
	valorTotal.innerHTML = `R$${valorFinal}`;	
		
});

document.getElementById("retirada").addEventListener("click", async () => {
	
	let valorFinal = valorTexto.replace("R$", "");
	
	valorTotal.innerHTML = `R$${valorFinal}`;	
		
});



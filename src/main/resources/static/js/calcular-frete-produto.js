document.getElementById("calcular-frete").addEventListener("click", async () => {
	
	const cep = document.querySelector('#cep');
	const cepNumeros = cep.value.replace("-", "");
	
	const sedexPreco = document.getElementById("sedex-preco");
	const sedexDias = document.getElementById("sedex-dias");
	const jadlogPreco = document.getElementById("jadlog-preco");
	const jadlogDias = document.getElementById("jadlog-dias");
	const resultadoDiv = document.querySelector(".resultado-frete");
	
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
            
			let nomeServico1, valor1, tempoEntrega1;
			let nomeServico2, valor2, tempoEntrega2;
			
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
			resultadoDiv.removeAttribute("hidden");
			sedexPreco.innerHTML = `R$${valor1}`;
			sedexDias.innerHTML = `${tempoEntrega1} dias úteis`;
			
			jadlogPreco.innerHTML = `R$${valor2}`;
			jadlogDias.innerHTML = `${tempoEntrega2} dias úteis`;
			
        } else {
            console.error("Erro ao calcular frete:", await response.text());
        }
    } catch (error) {
        console.error("Erro na requisição:", error);
    }
});

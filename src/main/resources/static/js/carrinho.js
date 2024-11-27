document.addEventListener("DOMContentLoaded", async () => {
    const iconeCarrinho = document.querySelector("a[href='/carrinho']");

    try {
        const response = await fetch("/carrinho/contador", {
            method: "GET",
            credentials: "include",
        });

        if (response.ok) {
            const contador = await response.json();
            iconeCarrinho.setAttribute("data-contador", contador);
        }
    } catch (error) {
        console.error("Erro ao buscar contador do carrinho:", error);
    }

    const botoesCarrinho = document.querySelectorAll(".add-carrinho");

    botoesCarrinho.forEach(botao => {
        botao.addEventListener("click", async (event) => {
            event.preventDefault();

            const produtoId = botao.getAttribute("data-produto-id");

            try {
                const response = await fetch(`/carrinho/adicionar?produtoId=${produtoId}`, {
                    method: "GET",
                    credentials: "include",
                });

                if (response.ok) {
                    if (iconeCarrinho) {
                        let contador = iconeCarrinho.getAttribute("data-contador") || 0;
                        contador++;
                        iconeCarrinho.setAttribute("data-contador", contador);
                        iconeCarrinho.classList.add("carrinho-atualizado");
                    }
                } else {
                    alert("Erro ao adicionar ao carrinho. Tente novamente.");
                }
            } catch (error) {
                console.error("Erro na requisição:", error);
                alert("Erro ao adicionar ao carrinho. Tente novamente mais tarde.");
            }
        });
    });
});

document.addEventListener("DOMContentLoaded", () => {
    const botoesExcluir = document.querySelectorAll(".botao-excluir");

    botoesExcluir.forEach(botao => {
        botao.addEventListener("click", async (event) => {
            event.preventDefault();

            const form = botao.closest("form");
            const produtoId = form.querySelector("input[name='produtoId']").value;

            try {
                const response = await fetch("/carrinho/remover", {
                    method: "POST",
                    headers: { "Content-Type": "application/x-www-form-urlencoded" },
                    body: new URLSearchParams({ produtoId }),
                });

                if (response.ok) {
                	const carrinhoIcone = document.querySelector(".icone-carrinho");
                    if (carrinhoIcone) {
                        let contador = parseInt(carrinhoIcone.getAttribute("data-contador")) || 0;
                        contador = Math.max(0, contador - 1);
                        carrinhoIcone.setAttribute("data-contador", contador);
                    }

                    const detalhe = form.closest(".detalhamento");
                    if (detalhe) {
						const preco = detalhe.querySelector(".valor").textContent.replace("R$", "").trim();
                        const precoNum = parseFloat(preco.replace(",", "."));

                        const valorTotalElement = document.querySelector("#valor-total");
                        if (valorTotalElement) {
                            let valorTotal = parseFloat(valorTotalElement.textContent.replace("R$", "").trim().replace(",", "."));
                            valorTotal = Math.max(0, valorTotal - precoNum);
                            valorTotalElement.textContent = `R$${valorTotal.toFixed(2).replace(".", ",")}`;
                        }
                        detalhe.remove();
                    }

                } else {
                    alert("Erro ao remover o produto. Tente novamente.");
                }
            } catch (error) {
                console.error("Erro na requisição:", error);
                alert("Erro ao remover o produto. Tente novamente mais tarde.");
            }
        });
    });
});

document.addEventListener("DOMContentLoaded", () => {
    const botaoComprar = document.getElementById("bt-comprar");

    botaoComprar.addEventListener("click", async (event) => {
        event.preventDefault();

        const produtoId = botaoComprar.getAttribute("data-produto-id");

        try {
            const response = await fetch(`/carrinho/adicionar?produtoId=${produtoId}`, {
                method: "GET",
                credentials: "include",
            });

            if (response.ok) {
				const iconeCarrinho = document.querySelector(".icone-carrinho");
                if (iconeCarrinho) {
                    let contador = iconeCarrinho.getAttribute("data-contador") || 0;
                    contador++;
                    iconeCarrinho.setAttribute("data-contador", contador);
                    iconeCarrinho.classList.add("carrinho-atualizado");
					window.location.href = '/carrinho';
                }
            } else {
                alert("Erro ao adicionar ao carrinho. Tente novamente.");
            }
        } catch (error) {
            console.error("Erro na requisição:", error);
            alert("Erro ao adicionar ao carrinho. Tente novamente mais tarde.");
        }
    });
});
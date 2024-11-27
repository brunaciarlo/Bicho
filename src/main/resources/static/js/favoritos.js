document.addEventListener("DOMContentLoaded", async () => {
    await carregarFavoritos();
});

async function carregarFavoritos() {
    try {
        const response = await fetch("/favoritos/lista", {
            method: "GET",
            credentials: "include",
        });

        if (response.ok) {
            const favoritos = await response.json();

            document.querySelectorAll(".favoritar, .desfavoritar").forEach(botao => {
                const produtoId = botao.getAttribute("data-produto-id");

                if (favoritos.includes(Number(produtoId))) {
                    alterarParaDesfavoritar(botao);
                } else {
                    alterarParaFavoritar(botao);
                }
            });
        }
    } catch (error) {
        console.error("Erro ao carregar favoritos:", error);
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const botoesFavorito = document.querySelectorAll(".favoritar");

    botoesFavorito.forEach(botao => {
        botao.addEventListener("click", async (event) => {
            event.preventDefault();

            const produtoId = botao.getAttribute("data-produto-id");

            try {
                const response = await fetch(`/favoritos/adicionar?produtoId=${produtoId}`, {
                    method: "GET",
                    credentials: "include",
                });

                if (response.ok) {
					alterarParaDesfavoritar(botao);
					window.location.reload();
                } else if (response.status === 401) {
                    window.location.href = "/login";
                } else {
                    alert("Erro ao adicionar aos favoritos. Tente novamente.");
                }
            } catch (error) {
                console.error("Erro na requisição:", error);
                alert("Erro ao adicionar aos favoritos. Tente novamente mais tarde.");
            }
        });
    });
});

document.addEventListener("DOMContentLoaded", () => {
    const botoesDesfavorito = document.querySelectorAll(".desfavoritar");

    botoesDesfavorito.forEach(botao => {
        botao.addEventListener("click", async (event) => {
            event.preventDefault();

            const produtoId = botao.getAttribute("data-produto-id");

            try {
                const response = await fetch(`/favoritos/remover?produtoId=${produtoId}`, {
                    method: "DELETE",
                    credentials: "include",
                });

                if (response.ok) {
					alterarParaFavoritar(botao);
					window.location.reload();
                } else {
                    alert("Erro ao remover dos favoritos. Tente novamente.");
                }
            } catch (error) {
                console.error("Erro na requisição:", error);
                alert("Erro ao remover dos favoritos. Tente novamente mais tarde.");
            }
        });
    });
});

function alterarParaDesfavoritar(botao) {
    botao.classList.remove("favoritar");
    botao.classList.add("desfavoritar");
    botao.innerHTML = '<img src="/imagens/desfavorite.png" alt="Desfavoritar">';

    botao.addEventListener("click", async (event) => {
        event.preventDefault();
        const produtoId = botao.getAttribute("data-produto-id");

        try {
            const response = await fetch(`/favoritos/remover?produtoId=${produtoId}`, {
                method: "DELETE",
                credentials: "include",
            });

            if (response.ok) {
                alterarParaFavoritar(botao);
            } else {
                alert("Erro ao desfavoritar o produto.");
            }
        } catch (error) {
            console.error("Erro ao desfavoritar:", error);
        }
    });
}

function alterarParaFavoritar(botao) {
    botao.classList.remove("desfavoritar");
    botao.classList.add("favoritar");
    botao.innerHTML = '<img src="/imagens/favorite.png" alt="Favoritar">';

    botao.addEventListener("click", async (event) => {
        event.preventDefault();
        const produtoId = botao.getAttribute("data-produto-id");

        try {
            const response = await fetch(`/favoritos/adicionar?produtoId=${produtoId}`, {
                method: "GET",
                credentials: "include",
            });

            if (response.ok) {
                alterarParaDesfavoritar(botao);
            } else {
                alert("Erro ao favoritar o produto.");
            }
        } catch (error) {
            console.error("Erro ao favoritar:", error);
        }
    });
}


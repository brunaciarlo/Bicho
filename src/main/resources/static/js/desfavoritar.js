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

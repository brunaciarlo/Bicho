document.getElementById("botao-buscar").addEventListener("click", function () {
    const termo = document.getElementById("input-buscar").value;

    if (termo.trim() === "") {
        alert("Digite algo para buscar!");
        return;
    }

    window.location.href = `/buscar?termo=${encodeURIComponent(termo)}`;
});

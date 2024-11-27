document.querySelectorAll('.botoes button').forEach(button => {
    button.addEventListener('click', e => e.stopPropagation());
});
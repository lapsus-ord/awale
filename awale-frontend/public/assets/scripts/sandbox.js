let element = document.getElementById("bouton");

element.addEventListener("mouseover", HandlerMouseover);
element.addEventListener("click", HandlerClick);

function HandlerMouseover() {
    console.log("Souris par-dessus");
}

function HandlerClick() {
    console.log("AÏE");
}
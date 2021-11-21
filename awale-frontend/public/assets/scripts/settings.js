let btnPopup = document.getElementById('btnPopup');
let overlay = document.getElementById('overlay');
btnPopup.addEventListener('click',function (){
    overlay.style.display='block';
});

let btnClose = document.getElementById('btnClose');
btnClose.addEventListener('click', function (){
    overlay.style.display = 'none';
});

let slctSound = document.getElementById('sound');
slctSound.addEventListener('change', function (){
    let audio = new Audio("resources/sounds/Caraibes.mp3");
    audio.play();
});

let iptVolume = document.getElementById('volume');
iptVolume.addEventListener('change', function (e){

});





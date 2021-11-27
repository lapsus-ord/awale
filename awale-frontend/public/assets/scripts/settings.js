let btnPopup = document.getElementById('btnPopup');
let overlay = document.getElementById('overlay');
btnPopup.addEventListener('click',function (){
    overlay.style.display='block';
});

let btnClose = document.getElementById('btnClose');
btnClose.addEventListener('click', function (){
    overlay.style.display = 'none';
});

/*let submitBtn = document.getElementById('click');
submitBtn.addEventListener('click', function (){
    let audio = document.getElementById('audio');
    audio.pause();
    let music = document.getElementById('music');
    let volume = document.getElementById('volume');
    audio.setAttribute('volume', ''+volume);
    audio.setAttribute('src', 'assets/sounds/'+music);
    audio.removeAttribute('autoplay');
    audio.play();
})*/

let submitBtn = document.getElementById('submit');
submitBtn.addEventListener('click', function (){
    let audio = document.getElementById('audio');
    audio.pause();
    let music = document.getElementById('music');
    let volume = document.getElementById('volume');
    audio.volume = parseFloat(volume.value/10);
    audio.src = 'assets/sounds/'+music.options[music.selectedIndex].value+'.mp3';
    audio.play();
})





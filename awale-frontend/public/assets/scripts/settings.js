let btnPopup = document.querySelector('#btnPopup');
let overlay = document.querySelector('.center.modal');
btnPopup.addEventListener('click', () => {
  console.log('l)')
  overlay.style.display = 'block';
});

let btnClose = document.querySelector('#btnClose');
btnClose.addEventListener('click', () => {
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

let submitBtn = document.querySelector('#soundSubmit');
submitBtn.addEventListener('click', function () {
  let audio = document.getElementById('audio');
  audio.pause();
  let music = document.getElementById('music');
  let volume = document.getElementById('volume');
  audio.volume = parseFloat(volume.value / 10);
  audio.src = 'assets/sounds/' + music.options[music.selectedIndex].value + '.mp3';
  audio.play();
})





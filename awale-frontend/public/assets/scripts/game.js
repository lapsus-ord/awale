// Connexion au WS
let socket = new WebSocket("wss://webinfo.iutmontp.univ-montp2.fr:2232/stomp");
let client = Stomp.over(socket);


// Envoi du message
let row = document.querySelectorAll('.user>div');

row.forEach(child => {
  child.addEventListener('click', handleClick)
});

function handleClick(e) {
  let jsonClick = {hole: null, value: null};
  jsonClick.hole = e.target.className.slice(-1);
  jsonClick.value = e.target.innerHTML;
  client.send('/app/send-hole', {}, JSON.stringify(jsonClick));
}
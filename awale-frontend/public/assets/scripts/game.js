// Connexion au WS
let socket = new SockJS('http://localhost:8080/stomp');
let client = Stomp.over(socket);

client.connect({}, frame => {
  console.log('[OPEN] Connexion avec le WS : ' + frame);
});


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
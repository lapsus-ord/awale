// --- Variables Globales ---
let user_id = readCookie("user_id");
let port = 63221;

// --- Connexion au WS ---
// ws://webinfo.iutmontp.univ-montp2.fr:${port}/endpoint
let wsJoinGame = new WebSocket(`ws://localhost:${port}/join`);
let wsPlay = new WebSocket(`ws://localhost:${port}/play`);


// --- Joindre/Création de la partie ---
window.addEventListener('load', joinGame);
function joinGame(e) {
  let json = {userId: ""}
  wsJoinGame.send(JSON.stringify("à écrire"));
}


// --- Envoi d'un coup ---
let row = document.querySelectorAll('.user>div');

row.forEach(child => {
  child.addEventListener('click', sendHole)
});
function sendHole(e) {
  let jsonClick = {userId: user_id, hole: null, value: null};
  jsonClick.hole = e.target.className.slice(-1);
  jsonClick.value = e.target.innerHTML;
  wsPlay.send(JSON.stringify(jsonClick));
}


// --- Utils ---
function createCookie(name, value, days) {
  let expires = "";
  if (days) {
    let date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    expires = "; expires=" + date.toDateString();
  }
  document.cookie = name + "=" + value + expires + "; path=/";
}

function readCookie(name) {
  let nameEQ = name + "=";
  let ca = document.cookie.split(';');
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) === ' ') c = c.substring(1, c.length);
    if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
  }
  return null;
}

function eraseCookie(name) {
  createCookie(name, "", -1);
}
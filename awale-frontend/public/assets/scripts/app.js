import * as cookie from './utils.js';
import {GameWS} from './GameWS.js';

// --- Variables Globales ---
let user_id = cookie.readCookie("user_id");
let username = cookie.readCookie("username") ?? "anonymous";
// url: ws://webinfo.iutmontp.univ-montp2.fr:${port}/endpoint
let url = `ws://localhost:${63221}/play`;
let ws = null;

// --- Connexion au WS ---
ws = new GameWS(url);
ws.connect(user_id, username);

// --- Envoi d'un coup ---
let row = document.querySelectorAll('.user>div');

row.forEach(child => {
  child.addEventListener('click', (ev => {
    ws.move(user_id, parseInt(ev.target.className.slice(-1)));
  }));
});

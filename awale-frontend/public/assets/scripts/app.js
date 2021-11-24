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
ws.getOnMessage(ev => mappingGameOBJ(JSON.parse(ev.data)));

// --- Envoi d'un coup ---
let row = document.querySelectorAll('.cell');

row.forEach(child => {
  child.addEventListener('click', (ev => {
    ws.move(user_id, parseInt(ev.target.className.slice(-1)));
  }));
});

// Associe les données de l'objet gameState au HTML
function mappingGameOBJ(game) {
  let board = game.gameState ?? [[0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0]];
  let i = 0;
  for (let cell of document.querySelectorAll('.player1>.cell')) {
    cell.textContent = board[0][i] + "";
    i++;
  }
  i = 0;
  for (let cell of document.querySelectorAll('.player2>.cell')) {
    cell.textContent = board[1][i] + "";
    i++;
  }
  document.querySelector('#state').textContent = game.state.toString();
  let p1 = game.players.player1 ?? {username: " ", score: 0};
  let p2 = game.players.player2 ?? {username: " ", score: 0};
  document.querySelector('.player1 .username').textContent = p1.username;
  document.querySelector('.player1 .username+.score').textContent = p1.score;
  document.querySelector('.player2 .username').textContent = p2.username;
  document.querySelector('.player2 .username+.score').textContent = p2.score;
}
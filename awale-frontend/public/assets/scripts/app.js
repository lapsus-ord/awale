import {GameWS} from './GameWS.js';
import * as Utils from './utils.js';

// --- Variables Globales ---
let req1 = await fetch('?action=userid&controller=user');
let req2 = await fetch('?action=username&controller=user');
let user_id = await req1.text();
let username = await req2.text();
// url: ws://webinfo.iutmontp.univ-montp2.fr:${port}/endpoint
let url = `ws://localhost:${63221}/play`;
let ws = null;
let gameId = Utils.getRequest('gameId');

// --- Connexion au WS ---
ws = new GameWS(url);
ws.connect(user_id, username, gameId);
ws.getOnMessage(ev => mappingGameOBJ(JSON.parse(ev.data)));

// --- Envoi d'un coup ---
let row = document.querySelectorAll('.cell');
row.forEach(child => {
  child.addEventListener('click', (ev => {
    ws.move(user_id, parseInt(ev.target.className.slice(-1)), gameId);
  }));
});

// Associe les données de l'objet gameState au HTML
function mappingGameOBJ(game) {
  console.log(game);
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
  document.querySelector('#state').textContent = Utils.convertState(game.state.toString());
  document.querySelector('.player1 .user').textContent = Utils.printPlayer(game.players.player1);
  document.querySelector('.player2 .user').textContent = Utils.printPlayer(game.players.player2);
}
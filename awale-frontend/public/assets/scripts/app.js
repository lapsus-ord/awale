import {GameWS} from './GameWS.js';
import * as Utils from './utils.js';

// --- Variables Globales ---
let req1 = await fetch('?action=userid&controller=user');
let req2 = await fetch('?action=username&controller=user');
let user_id = await req1.text();
let username = await req2.text();
let gameId = Utils.getRequest('gameId');
let url = `ws://${Utils.url_backend}/play`;
let ws = null;

// --- Connexion au WS ---
ws = new GameWS(url);
ws.connect(user_id, username, gameId);
ws.getOnMessage(mappingGameOBJ, redirectToWinPage);

// --- Envoi d'un coup ---
let row = document.querySelectorAll('.cell');
row.forEach(child => {
  child.addEventListener('click', (ev => {
    ws.move(user_id, parseInt(ev.target.className.slice(-1)), gameId);
  }));
});

// Associe les données de l'objet gameState au HTML
function mappingGameOBJ(game) {
  // On vérifie si la partie est finie
  if (game.state.toString() === 'END_GAME') {
    ws.end(gameId);
  }

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
  let p1Infos = Utils.getPlayerInfos(game.players.player1);
  let p2Infos = Utils.getPlayerInfos(game.players.player2);
  document.querySelector('.player1 .user').querySelector('.username').textContent = p1Infos[0];
  document.querySelector('.player1 .user').querySelector('.score').textContent = p1Infos[1];
  document.querySelector('.player2 .user').querySelector('.username').textContent = p2Infos[0];
  document.querySelector('.player2 .user').querySelector('.score').textContent = p2Infos[1];
}

function redirectToWinPage(json) {
  let form = document.createElement("form");
  form.method = 'post';
  form.action = '?action=result';
  let resultInput = document.createElement('input');
  resultInput.type = "text";
  resultInput.name = "result";
  resultInput.value = json.result.toString();
  let player1Input = document.createElement('input');
  player1Input.type = "text";
  player1Input.name = "player1";
  player1Input.value = Utils.getPlayerInfos(json.players.player1)[0] + ' avec un score de ' + Utils.getPlayerInfos(json.players.player1)[1] + ' points !';
  let player2Input = document.createElement('input');
  player2Input.type = "text";
  player2Input.name = "player2";
  player2Input.value = Utils.getPlayerInfos(json.players.player2)[0] + ' avec un score de ' + Utils.getPlayerInfos(json.players.player2)[1] + ' points !';
  form.appendChild(resultInput);
  form.appendChild(player1Input);
  form.appendChild(player2Input);
  form.hidden = true;
  document.body.appendChild(form);
  form.submit();
}

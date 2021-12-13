import {convertState, printPlayer} from './utils.js';

// url: ws://webinfo.iutmontp.univ-montp2.fr:${port}/endpoint
let api_url = `http://localhost:${63221}/waiting-games`;

let response = await fetch(api_url);
let list = await response.json();

let dom_list = document.querySelector('#list-of-games');
for (let game of list) {
  console.log(game);
  dom_list.innerHTML += `
<a href="?action=play&gameId=${game.gameId}" class="game-select">
  <div class="state">État&nbsp;: ${convertState(game.state)}</div>
  <div class="players">
    <span>Joueur 1&nbsp;: ${printPlayer(game.players.player1)}</span>
    <span>Joueur 2&nbsp;: ${printPlayer(game.players.player2)}</span>
  </div>
</a>`;
}

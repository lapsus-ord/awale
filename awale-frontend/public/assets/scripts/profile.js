import {convertState, getPlayerInfos} from './utils.js';

let req1 = await fetch('?action=userid&controller=user');
let user_id = await req1.text();
let api_url = `http://localhost:${63221}/${user_id}/games`;
let response = await fetch(api_url);
let list = await response.json();

let dom_list = document.querySelector('#list-of-games');
for (let game of list) {
	console.log(game);
	dom_list.innerHTML += `
<a href="?action=play&gameId=${game.gameId}" class="game-select">
  <div class="state">État&nbsp;: ${convertState(game.state)}</div>
  <div class="players">
    <span>Joueur 1&nbsp;: ${getPlayerInfos(game.players.player1)[0]}</span>
    <span>Joueur 2&nbsp;: ${getPlayerInfos(game.players.player2)[0]}</span>
  </div>
</a>`;
}

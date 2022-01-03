import {objEnd, objJoin, objJoinBot, objMove} from './data.js';
import {getRequest, isInRequest} from "./utils.js";

export class GameWS {
  #ws = null;

  constructor(url) {
    this.#ws = new WebSocket(url);
  }

  connect(userId, username, gameId) {
    console.log("CONNECT %o", this.#ws.url);

    this.#ws.onerror = (() => alert("Problème de connexion avec le serveur ❌"));
    this.#ws.onopen = (() => {
      console.log("Connexion réussie ✅");
      if (isInRequest('level')) {
        this.#joinBotGame(userId, username, gameId, getRequest('level'));
      } else {
        this.#join(userId, username, gameId);
      }
    });
    this.#ws.onclose = (() => console.log("Vous n'êtes plus connectés au serveur ❌"));
  }

  end(gameId) {
    if (this.#ws !== null) {
      console.log("END %o", this.#ws.url);
      this.#sendToWS("end," + JSON.stringify(objEnd(gameId)));
    }
  }

  #join(userId, username, gameId) {
    if (this.#ws !== null) {
      console.log("JOIN %o: user=%s", this.#ws.url, userId);
      this.#sendToWS("join," + JSON.stringify(objJoin(userId, username, gameId)));
    } else {
      alert("Erreur : Vous n'êtes pas connecté au serveur.");
    }
  }

  #joinBotGame(userId, username, gameId, level) {
    if (this.#ws !== null) {
      console.log("JOIN-BOT %o: user=%s", this.#ws.url, userId);
      this.#sendToWS("join-bot," + JSON.stringify(objJoinBot(userId, username, gameId, level)));
    } else {
      alert("Erreur : Vous n'êtes pas connecté au serveur.");
    }
  }

  move(userId, holeChosen, gameId) {
    if (this.#ws !== null) {
      console.log("MOVE %o: user=%s, hole=%i", this.#ws.url, userId, holeChosen);
      this.#sendToWS("move," + JSON.stringify(objMove(userId, holeChosen, gameId)));
    } else {
      alert("Erreur : Vous n'êtes pas connecté au serveur.");
    }
  }

  #sendToWS(payload) {
    if (this.#ws !== null) {
      console.log("SEND %o: %s", this.#ws.url, payload);
      this.#ws.send(payload);
    } else {
      alert("Erreur : Vous n'êtes pas connecté au serveur.");
    }
  }

  getOnMessage(callback1, callback2) {
    this.#ws.onmessage = ((ev) => {
      let data = ev.data.split(/,(.+)/);
      if (data[0] === 'update') {
        callback1(JSON.parse(data[1]));
      } else if (data[0] === 'winConfirmed') {
        console.log(data[1]);
        callback2(JSON.parse(data[1]));
      } else if (data[0] === 'error') {
        alert('Erreur : ' + data[1]);
      }
    });
  }
}

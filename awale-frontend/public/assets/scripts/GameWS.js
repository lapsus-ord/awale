import {objJoin, objJoinBot, objMove} from './data.js';
import {getRequest, isInRequest} from "./utils.js";

export class GameWS {
  #ws = null;

  constructor(url) {
    this.#ws = new WebSocket(url);
  }

  connect(userId, username, gameId) {
    console.log("CONNECT %o", this.#ws.url);

    this.#ws.onerror = (() => console.log("Problème de connexion avec le serveur ❌"));
    this.#ws.onopen = (() => {
      console.log("Connexion réussie ✅");
      if (isInRequest('level')) {
        console.log('ici');
        this.#joinBotGame(userId, username, gameId, getRequest('level'));
      } else {
        this.#join(userId, username, gameId);
      }
    });
    this.#ws.onclose = (() => {
      this.disconnect()
    });
  }

  disconnect() {
    console.log("DISCONNECT %o", this.#ws.url);
    if (this.#ws !== null) {
      this.#ws.close();
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

  getOnMessage(type, callback) {
    this.#ws.onmessage = ((ev) => {
      let data = ev.data.split(/,(.+)/);
      if (data[0] === type) {
        callback(data[1]);
      }
    });
  }
}

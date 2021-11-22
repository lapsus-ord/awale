import {objJoin, objMove} from './data.js';

export class GameWS {
  #ws = null;

  constructor(url) {
    this.#ws = new WebSocket(url);
  }

  connect(userId) {
    console.log("CONNECT %o", this.#ws.url);

    this.#ws.onerror = (() => console.log("Problème de connexion avec le serveur ❌"));
    this.#ws.onopen = (() => {
      console.log("Connexion réussie ✅");
      this.#join(userId);
    });
    this.#ws.onclose = (() => this.disconnect());
    this.#ws.onmessage = (ev => console.log("MSG-RCVD: %s", ev.data));
  }

  disconnect() {
    console.log("DISCONNECT %o", this.#ws.url);
    if (this.#ws !== null) {
      this.#ws.close();
    }
  }

  #join(userId) {
    if (this.#ws !== null) {
      console.log("JOIN %o: user=%s", this.#ws.url, userId);
      this.#sendToWS("join," + JSON.stringify(objJoin(userId)));
    } else {
      alert("Erreur : Vous n'êtes pas connecté au serveur.");
    }
  }

  move(userId, holeChosen) {
    if (this.#ws !== null) {
      console.log("MOVE %o: user=%s, hole=%i", this.#ws.url, userId, holeChosen);
      this.#sendToWS("move," + JSON.stringify(objMove(userId, holeChosen)));
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
}

// Ici se trouve les différents Objets à envoyer au serveur

// Quand un joueur rejoint une partie
// TODO: "gameID": <gameId> à ajouter pour la gestion de plusieurs parties
export function objJoin(userId, username) {
  return {
    userId: userId,
    username: username
  };
}

// Quand un joueur envoie un coup à jouer
// TODO: Ajouter le token pour confirmer que c'est bien au joueur de jouer
//       "gameID": <gameId> à ajouter pour la gestion de plusieurs parties
export function objMove(userId, holeChosen) {
  return {
    userId: userId,
    hole: holeChosen
  };
}

/*
Réception du message du serveur
TODO: Ajouter un token pour le joueur qui doit jouer
  si    token === null : c'est pas son tour
  sinon token === "3ededca6dcc6" : c'est son tour
 */
export function objGameState(payload) {
  return JSON.parse(payload);
}
// Ici se trouve les différents Objets à envoyer au serveur

// Quand un joueur rejoint une partie
export function objJoin(userId, username, gameId) {
  return {
    userId: userId,
    username: username,
    gameId: gameId
  };
}

// Quand un joueur se déconnecte d'une partie
export function objDisconnect(userId, username, gameId) {
  return {
    userId: userId,
    gameId: gameId
  };
}

// Quand un joueur envoie un coup à jouer
// TODO: Ajouter le token pour confirmer que c'est bien au joueur de jouer
export function objMove(userId, holeChosen, gameId) {
  return {
    userId: userId,
    hole: holeChosen,
    gameId: gameId
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
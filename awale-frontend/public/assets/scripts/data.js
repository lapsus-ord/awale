// Ici se trouve les différents Objets à envoyer au serveur

// Quand un joueur rejoint une partie
export function objJoin(userId, username, gameId) {
  return {
    userId: userId,
    username: username,
    gameId: gameId
  };
}

// Quand un joueur rejoint une partie avec un Bot
export function objJoinBot(userId, username, gameId, level) {
  return {
    userId: userId,
    username: username,
    gameId: gameId,
    level: level
  };
}

// Quand on veut savoir le gagnant d'une partie
export function objEnd(gameId) {
  return {
    gameId: gameId
  };
}

// Quand un joueur envoie un coup à jouer
export function objMove(userId, holeChosen, gameId) {
  return {
    userId: userId,
    hole: holeChosen,
    gameId: gameId
  };
}

// Réception du message du serveur
export function objGameState(payload) {
  return JSON.parse(payload);
}
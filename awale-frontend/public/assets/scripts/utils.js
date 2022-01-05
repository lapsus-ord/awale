// Adresse Backend
const port_backend = '63221';
export const url_backend = 'localhost' + ':' + port_backend;

// --- Traduction Serveur ---
export function convertState(state) {
  if (state === 'WAITING_GAME')
    return 'En attente';
  else if (state === 'PLAYER1_TURN')
    return 'Tour du joueur 1';
  else if (state === 'PLAYER2_TURN')
    return 'Tour du joueur 2';
  else if (state === 'END_GAME')
    return 'Partie finie';
  return 'État inconnu';
}

export function getPlayerInfos(player) {
  if (player === null)
    return ['vide', 0];
  return [player.username, player.score];
}

// --- Renvoi la présence du String en paramètre dans l'url GET ---
export function isInRequest(str) {
  let bool = false;
  window.location.href.split('?')[1].split('&').forEach((el) => {
    let tmp = el.split('=');
    if (tmp[0] === str) bool = true;
  });
  return bool;
}

// --- Retourne la valeur de la clé dans l'url GET ---
export function getRequest(key) {
  let array = [];
  window.location.href.split('?')[1].split('&').forEach((el) => {
    let tmp = el.split('=');
    array[tmp[0]] = tmp[1];
  });
  return array[key];
}

// --- Cookies ---
export function createCookie(name, value, days) {
  let expires = "";
  if (days) {
    let date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    expires = "; expires=" + date.toDateString();
  }
  document.cookie = name + "=" + value + expires + "; path=/";
}

export function readCookie(name) {
  let nameEQ = name + "=";
  let ca = document.cookie.split(';');
  for (let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) === ' ') c = c.substring(1, c.length);
    if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
  }
  return null;
}

export function eraseCookie(name) {
  createCookie(name, "", -1);
}

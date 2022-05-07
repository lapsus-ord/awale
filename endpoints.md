## Les différents endpoints de l'API Websocket/REST :

### Introduction

Les messages envoyés au serveur (ou au client) seront toujours composés de 2 parties :
- La **1<sup>ère</sup> partie** correspond à la **commande** à exécuter (ex: `join`, `move`…).
- La **2<sup>ème</sup> partie** correspond au contenu du message.

**Exemple :** (pour rejoindre une partie)
```
join,{"userId": "p_61994e35a6005", "username": "Bernard", "gameId": "fe7d63be-e751"}
```

<br>

**Légende :**\
**S** = Serveur ; **U** = Utilisateur

<hr>
<br>

<details>
<summary>Endpoints relatifs au Websocket</summary>

### Rejoindre une partie (U->S)

Commande : `join`\
Contenu :
```json
{
    "userId": "<userId>",
    "username": "<username>",
    "gameId": "<gameId>"
}
```

### Rejoindre une partie contre une IA (U->S)

Commande : `join`\
Contenu :
```json
{
    "userId": "<userId>",
    "username": "<username>",
    "gameId": "<gameId>",
    "level": "<easy / medium / hard / extreme>",
}
```

### Jouer un coup (U->S)

Commande : `move`\
Contenu :
```json
{
    "userId": "<userId>",
    "hole": <int>,
    "gameId": "<gameId>"
}
```

### Demande de mis à jour de la partie (U->S)

Commande : `update`\
Contenu :
```json
{
    "gameId": "<gameId>"
}
```

### État de fin de partie (U->S)

Commande : `end`\
Contenu :
```json
{
    "gameId": "<gameId>"
}
```

<hr>

### État de la partie (S->U)

Commande : `update`\
Contenu :
```json
{
    "gameId": "<gameId>",
    "state": "<WAITING_GAME / PLAYER1_TURN / PLAYER2_TURN / END_GAME>",
    "players": {
        "player1": {
            "username": "<username>",
            "score": <int>
        },
        "player2": null
    },
    "gameState": [
        [4,4,4,4,4,4],
        [4,4,4,4,4,4]
    ]
}
```

### Envoi du gagnant (S->U)

Commande : `winConfirmed`\
Contenu :
```json
{
    "result": "<username / draw>",
    "players": {
        "player1": {
            "username": "<username>",
            "score": <int>
        },
        "player2": {
            "username": "<username>",
            "score": <int>
        }
    }
}
```

### Message d'erreur (S->U)

Commande : `error`\
Contenu : `Un simple texte comme message d'erreur`

</details>

<br>
<hr>
<br>

<details>
<summary>Endpoints relatifs à l'API HTTP</summary>

### Demande des parties (vs Joueurs) en attentes (U->S)

Commande : `/waiting-games`

### Demande des parties du joueur (qu'il participe ou observe) (U->S)

Commande : `/{userId}/games`

</details>

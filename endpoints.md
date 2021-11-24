## Les différents endpoints de l'API Websocket/REST :

### Introduction

Les messages envoyés au serveur (ou au client) seront toujours composés de 2 parties :
- La **1<sup>ère</sup> partie** correspond à la **commande** à exécuter (ex: `join`, `move`…).\
- La **2<sup>ème</sup> partie** correspond au contenu du message (souvent du JSON).

**TODO:** ajouter un diagramme de séquence/communication.

<hr>

<details>
<summary><h2>Endpoints relatifs au Websocket</h2></summary>

### Rejoindre une partie (Cl->S)

Commande : `join`\
Contenu :
```json
{
    "userId": "<userId>",
    "username": "<username>"
}
```
> **Retourne :** Le nouvel état de la partie.\
> **Commentaires :** `gameroomId` à ajouter pour la gestion de plusieurs parties en simultanées.

### Jouer un coup (Cl->S)

Commande : `move`\
Contenu :
```json
{
    "userId": "<userId>",
    "hole": <int>,
}
```
> **Retourne :** Le nouvel état de la partie.\
> **Commentaires :** `respTurnToken` à ajouter pour la permission de jouer (vérifier avec le token précédent si le joueur doit bien jouer).

### État de la partie (S->Cl)

Commande : `gameState`\
Contenu :
```json
{
    "state": "<STATE>",
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
> **Commentaires :** `turnToken` à ajouter pour la permission de jouer.

</details>
<hr>

<details>
<summary><h2>Endpoints relatifs au RESTful</h2></summary>

### Demande des parties (vs Joueurs) en attentes (Cl->S)

Commande : `listGames`\
Contenu :
```
À écrire…
```

### Demande des parties (vs Joueurs) en attentes (S->Cl)

Commande : `listGames`\
Contenu :
```
À écrire…
```

</details>

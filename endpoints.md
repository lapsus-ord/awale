## Les différents endpoints de l'API Websocket/REST :

### Introduction

Les messages envoyés au serveur (ou au client) seront toujours composés de 2 parties :
- La **1<sup>ère</sup> partie** correspond à la **commande** à exécuter (ex: `join`, `move`…).
- La **2<sup>ème</sup> partie** correspond au contenu du message.

**Exemple :** (pour rejoindre une partie)
```json
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
> **Retourne :** Le nouvel état de la partie.

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
> **Retourne :** Le nouvel état de la partie.

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

### Message d'erreur (S->Cl)

Commande : `error`\
Contenu :
```json
{
    "type": "<type de l'erreur>",
    "msg": "<message d'erreur>"
}
```

</details>

<br>

<details>
<summary>Endpoints relatifs à l'API HTTP</summary>

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

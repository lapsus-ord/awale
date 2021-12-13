package fr.solo.awale.server.services;

import fr.solo.awale.logic.Awale;
import fr.solo.awale.logic.GameFactory;
import fr.solo.awale.logic.player.AbstractPlayer;
import fr.solo.awale.logic.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class GameService {
    private static GameService instance;
    private HashMap<String, Awale> games;
    private HashMap<String, AbstractPlayer> players;

    private GameService() {
        games = new HashMap<>();
        players = new HashMap<>(2);
    }

    public static GameService getInstance() {
        if (instance == null) {
            instance = new GameService();
        }
        return instance;
    }

    public boolean joinGame(String userId, String username, String gameId) {
        if (!players.containsKey(userId)) {
            players.put(userId, new Player(username));
            if (games.containsKey(gameId)) { // Si la partie existe déjà
                players.get(userId).joinGame(games.get(gameId));
            } else {
                System.out.println("Création de la partie n°" + gameId);
                games.put(gameId, new GameFactory().createGame(players.get(userId)));
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean move(String playerId, int hole, String gameId) {
        System.out.println("playerId: " + playerId + ", trou: " + hole + ", gameId: " + gameId);
        if (games.get(gameId).hasTwoPlayers())
            return players.get(playerId).play(hole);
        else
            return false; // Le joueur ne peut pas jouer si la partie n'a pas 2 joueurs
    }

    public boolean isPlayerInTheGame(String userId, String gameId) {
        return games.get(gameId).isPlayerInTheGame(players.get(userId));
    }

    public String getJsonGame(String id) {
        return games.get(id).toJson(id);
    }

    public String getJsonGames() {
        StringJoiner strJ = new StringJoiner(",","[", "]");
        games.forEach((key, value) -> {
            strJ.add(value.toJson(key));
        });
        return strJ.toString();
    }

    public String getJsonWaitingGames() {
        StringJoiner strJ = new StringJoiner(",","[", "]");
        games.forEach((key, value) -> {
            if (value.getState().equals(Awale.Gamestate.WAITING_GAME))
                strJ.add(value.toJson(key));
        });
        return strJ.toString();
    }
}

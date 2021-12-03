package fr.solo.awale.server.services;

import fr.solo.awale.logic.Awale;
import fr.solo.awale.logic.player.AbstractPlayer;
import fr.solo.awale.logic.player.Player;

import java.util.HashMap;

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
            players.get(userId).joinGame(/* TODO: remplacer par un jeu créé de la HshMap */);
            return true;
        } else {
            return false;
        }
    }

    public boolean move(String id, int hole) {
        if (game.hasTwoPlayers())
            return players.get(id).play(hole);
        else
            return false; // Le joueur ne peut pas jouer si la partie n'a pas 2 joueurs
    }

    @Override
    public String toString() {
        return game.toString();
    }
}

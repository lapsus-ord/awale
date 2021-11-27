package fr.solo.awale.server.services;

import fr.solo.awale.logic.player.AbstractPlayer;
import fr.solo.awale.logic.player.Player;
import fr.solo.awale.server.models.AwaleWeb;

import java.util.HashMap;

public class GameService {
    private static GameService instance;
    private AwaleWeb game;
    private HashMap<String, AbstractPlayer> players;

    public GameService() {
        game = new AwaleWeb();
        new Thread(game).start();
        players = new HashMap<>(2);
    }

    public static GameService getInstance() {
        if (instance == null) {
            instance = new GameService();
        }
        return instance;
    }

    public boolean addPlayer(String id, String username) {
        if (!players.containsKey(id)) {
            players.put(id, new Player(username));
            players.get(id).joinGame(game);
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

package fr.solo.awale.server.services;

import fr.solo.awale.logic.player.AbstractPlayer;
import fr.solo.awale.logic.player.Player;
import fr.solo.awale.server.dtos.AwaleDto;

import java.util.HashMap;

import static com.diogonunes.jcolor.Ansi.colorize;

public class GameService {
    private static GameService instance;
    private AwaleDto game;
    private HashMap<String, AbstractPlayer> players;

    public GameService() {
        game = new AwaleDto();
        new Thread(game).start();
        players = new HashMap<>(2);
    }

    public static GameService getInstance() {
        if (instance == null) {
            instance = new GameService();
        }
        return instance;
    }

    public void addPlayer(String id, String username) {
        if (!players.containsKey(id)) {
            players.put(id, new Player(username));
            players.get(id).joinGame(game);
        } else {
            System.out.println(colorize("Le joueur a déjà rejoint la partie"));
        }
    }

    public void move(String id, int hole) {
        players.get(id).play(hole);
    }

    @Override
    public String toString() {
        return game.toString();
    }
}

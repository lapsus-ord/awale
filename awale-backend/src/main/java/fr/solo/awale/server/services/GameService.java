package fr.solo.awale.server.services;

import fr.solo.awale.logic.Awale;
import fr.solo.awale.logic.Side;
import fr.solo.awale.logic.player.AbstractPlayer;
import fr.solo.awale.logic.player.Player;

import java.util.HashMap;
import java.util.StringJoiner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class GameService {
    private static GameService instance;
    private Awale game;
    private HashMap<String, AbstractPlayer> players;

    public GameService() {
        game = new Awale();
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

    // --- JSON ---

    /**
     * Le retour de l'état d'une partie :<br>
     * <a href="https://gitlabinfo.iutmontp.univ-montp2.fr/donota/awale-projet/-/blob/5bec053e29b5018c4b85b282310759a7a908addc/endpoints.md">Plus d'explication ici</a>
     */
    @Override
    public String toString() {
        return "{ " +
                "\"state\": \"" + game.getState() + "\"," +
                "\"players\": {" +
                "\"player1\":" + playerToJson(game.getPlayer(Side.TOP)) + "," +
                "\"player2\":" + playerToJson(game.getPlayer(Side.BOTTOM)) +
                "}," +
                "\"gameState\":" + boardToJson(game.getBoard().getCells()) +
                " }";
    }

    private String playerToJson(AbstractPlayer player) {
        if (player == null) {
            return "null";
        }
        StringJoiner json = new StringJoiner(",", "{", "}");
        json.add("\"username\":\"" + player.getUsername() + "\"");
        json.add("\"score\":" + player.getScore());
        return json.toString();
    }

    private String boardToJson(int[][] board) {
        StringJoiner boardState = new StringJoiner(",", "[", "]");
        for (int[] row : board) {
            StringJoiner rowState = new StringJoiner(",", "[", "]");
            for (int j : row) {
                rowState.add(j + "");
            }
            boardState.add(rowState.toString());
        }
        return boardState.toString();
    }
}

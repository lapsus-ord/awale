package fr.solo.awale.server.services;

import fr.solo.awale.logic.Awale;
import fr.solo.awale.logic.Side;
import fr.solo.awale.logic.player.AbstractPlayer;
import fr.solo.awale.logic.player.Player;

import java.util.StringJoiner;

public class GameService {
    private static GameService instance;
    private Awale game;

    public GameService() {
        game = new Awale();
        new Thread(game).start();
    }

    public static GameService getInstance() {
        if (instance == null) {
            instance = new GameService();
        }
        return instance;
    }

    public void addPlayer(String id) {
        Player p = new Player(id);
        p.joinGame(game);
    }

    // --- JSON ---
    /**
     * Le retour de l'état d'une partie :
     * <pre>
     * {
     *   "players": {
     *     "player1": { "username": "Michel", "score": 0 },
     *     "player2": { "username": "Jacquie", "score": 0 },
     *   },
     *   "gameState": [[4, 4, 4, 4, 4, 4], [4, 4, 4, 4, 4, 4]]
     * }
     * </pre>
     */
    @Override
    public String toString() {
        return "{ " +
                "\"state\": " + game.getState() + "," +
                "\"players\": {" +
                "\"player1\":" + playerToJson(game.getPlayer(Side.TOP)) + "," +
                "\"player2\":" + playerToJson(game.getPlayer(Side.BOTTOM)) +
                "}," +
                "\"gameState:\"" + boardToJson(game.getBoard().getCells()) +
                " }";
    }

    private String playerToJson(AbstractPlayer player) {
        if (player == null) {
            return "null";
        }
        StringJoiner json = new StringJoiner(",", "{", "}");
        json.add("\"username\":" + player.getUsername());
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

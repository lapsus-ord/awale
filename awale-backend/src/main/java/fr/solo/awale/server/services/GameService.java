package fr.solo.awale.server.services;

import fr.solo.awale.logic.Awale;
import fr.solo.awale.logic.player.AbstractPlayer;

import java.util.StringJoiner;

public class GameService extends Awale {

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
    public String toJSON() {
        return "{ " +
                "\"players\": {" +
                "\"player1\":" + playerToJson(player1) +
                "\"player2\":" + playerToJson(player2) +
                "}" +
                "\"gameState:\"" + boardToJson(getBoard().getCells()) +
                " }";
    }

    private String playerToJson(AbstractPlayer player) {
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

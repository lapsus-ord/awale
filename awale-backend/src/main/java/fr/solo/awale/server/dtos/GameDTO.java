package fr.solo.awale.server.dtos;

import fr.solo.awale.logic.Awale;
import fr.solo.awale.logic.player.AbstractPlayer;

import java.util.StringJoiner;

public class GameDTO extends Awale {

    public String toJSON() {
        StringJoiner gameState = new StringJoiner(",", "[", "]");
        for (int[] row : super.getBoard().getCells()) {
            StringJoiner rowState = new StringJoiner(",", "[", "]");
            for (int j : row) {
                rowState.add(j + "");
            }
            gameState.add(rowState.toString());
        }
        return "{ " +
                "\"players\": {" +
                "\"player1\":" + playerToJson(player1) +
                "\"player2\":" + playerToJson(player2) +
                "}" +
                "\"gameState:\"" + gameState +
                " }";
    }

    private String playerToJson(AbstractPlayer player) {
        StringJoiner json = new StringJoiner(",", "{", "}");
        json.add("\"username\":" + player.getUsername());
        json.add("\"score\":" + player.getScore());
        return json.toString();
    }
}

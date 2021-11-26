package fr.solo.awale.server.dtos;

import fr.solo.awale.logic.Awale;
import fr.solo.awale.logic.player.AbstractPlayer;

import java.util.StringJoiner;

import static fr.solo.awale.server.dtos.AwaleDto.Gamestate.*;

public class AwaleDto extends Awale implements Runnable {
    private Gamestate state;

    enum Gamestate {
        WAITING, IN_GAME, END_GAME
    }

    @Override
    public void run() {
        state = WAITING;
        while (state.equals(WAITING)) {
            if (player1 != null && player2 != null)
                state = IN_GAME;
        }

        while (!state.equals(END_GAME)) {
            // TODO: Implémenter un système de tours entre les joueurs
        }

        state = END_GAME;
        seedDistribution();
        winner = checkWinner();
    }

    /**
     * Le retour de l'état d'une partie :<br>
     * <a href="https://gitlabinfo.iutmontp.univ-montp2.fr/donota/awale-projet/-/blob/5bec053e29b5018c4b85b282310759a7a908addc/endpoints.md">Plus d'explication ici</a>
     */
    @Override
    public String toString() {
        return "{ " +
                "\"state\": \"" + state + "\"," +
                "\"players\": {" +
                "\"player1\":" + playerToJson(player1) + "," +
                "\"player2\":" + playerToJson(player2) +
                "}," +
                "\"gameState\":" + boardToJson(board.getCells()) +
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

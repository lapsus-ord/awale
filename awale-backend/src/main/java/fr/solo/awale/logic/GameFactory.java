package fr.solo.awale.logic;

import fr.solo.awale.logic.player.AbstractPlayer;
import fr.solo.awale.logic.player.ai.AILevel;
import fr.solo.awale.logic.player.ai.DumbAI;
import fr.solo.awale.logic.player.ai.SmartAI;

public class GameFactory {
    public Awale createGame(AbstractPlayer player) {
        Awale game = new Awale();
        Thread gameThread = new Thread(game);
        gameThread.start();
        player.joinGame(game);
        return game;
    }

    public Awale createGameWithAI(AbstractPlayer player, AILevel level) throws Exception {
        Awale game = createGame(player);
        createAIPlayer(level).joinGame(game);
        return game;
    }

    public Awale createPrefabGame(int[][] board) throws Exception {
        Awale game = new Awale(board);
        Thread gameThread = new Thread(game);
        gameThread.start();
        return game;
    }

    private AbstractPlayer createAIPlayer(AILevel level) throws Exception {
        switch (level) {
            case EASY:
                return new DumbAI("Ougah-net");
            case MEDIUM:
                return new SmartAI("Trombettoni-net", 2);
            case HARD:
                return new SmartAI("Hisler-net", 4);
            case EXTREME:
                return new SmartAI("Joannides-net", 6);
            default:
                throw new Exception("Le level n'existe pas");
        }
    }
}

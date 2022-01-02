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
        switch (level) {
            case EASY:
                new DumbAI("Ougah-net").joinGame(game);
                break;
            case MEDIUM:
                new SmartAI("Trombettoni-net", 2).joinGame(game);
                break;
            case HARD:
                new SmartAI("Hisler-net", 4).joinGame(game);
                break;
            case EXTREME:
                new SmartAI("Joannides-net", 6).joinGame(game);
                break;
            default:
                throw new Exception("Le level n'existe pas");
        }
        return game;
    }
}

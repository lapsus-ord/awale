package fr.solo.awale.logic;

import fr.solo.awale.logic.player.AbstractPlayer;
import fr.solo.awale.logic.player.ai.DumbAI;
import fr.solo.awale.logic.player.ai.SmartAI;

public class GameFactory {
    public Awale createGame(AbstractPlayer player) {
        Awale game = new Awale();
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
                new SmartAI("Hisler-net", 2).joinGame(game);
                break;
            case HARD:
                new SmartAI("Sardoche-net", 4).joinGame(game);
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

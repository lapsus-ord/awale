package fr.solo.awale.logic;

import fr.solo.awale.logic.player.AbstractPlayer;
import fr.solo.awale.logic.player.ai.DumbAI;
import fr.solo.awale.logic.player.ai.SmartAI;

@Deprecated
public class GameBuilder {
    private Awale game;

    /**
     * @param player Le joueur qui a demandé à créer la partie est ajouté en premier.
     */
    public GameBuilder(AbstractPlayer player) {
        game = new Awale();
        player.joinGame(game);
    }

    public GameBuilder withAI(AILevel level) throws Exception {
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
        return this;
    }

    public Awale build() {
        return game;
    }
}

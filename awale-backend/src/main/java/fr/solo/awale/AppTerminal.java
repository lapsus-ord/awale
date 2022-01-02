package fr.solo.awale;

import fr.solo.awale.logic.Awale;
import fr.solo.awale.logic.GameFactory;
import fr.solo.awale.logic.player.AbstractPlayer;
import fr.solo.awale.logic.player.Player;

import static com.diogonunes.jcolor.Attribute.TEXT_COLOR;


public class AppTerminal {
    public static void main(String[] args) throws Exception {
        AbstractPlayer p1 = new Player("Bernard", TEXT_COLOR(42, 157, 143));
        AbstractPlayer p2 = new Player("Michel", TEXT_COLOR(255, 150, 255));

        Awale jeu = new GameFactory().createGame(p1);
        p2.joinGame(jeu);
    }
}

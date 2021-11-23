package fr.solo.awale;

import fr.solo.awale.logic.Awale;
import fr.solo.awale.logic.player.AbstractPlayer;
import fr.solo.awale.logic.player.Player;
import fr.solo.awale.logic.player.ai.SmartAI;

import static com.diogonunes.jcolor.Attribute.TEXT_COLOR;

public class AppTerminal {
    public static void main(String[] args) {
        AbstractPlayer p1 = new Player("Bernard", TEXT_COLOR(42, 157, 143));
        AbstractPlayer p2 = new SmartAI("Skynet", 4);

        Awale jeu = new Awale();
        p1.joinGame(jeu);
        p2.joinGame(jeu);

        try {
            jeu.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package fr.solo.awale;

import fr.solo.awale.player.ai.DumbAI;
import fr.solo.awale.player.ai.SmartAI;
import fr.solo.awale.player.AbstractPlayer;
import fr.solo.awale.player.Player;

import static com.diogonunes.jcolor.Attribute.TEXT_COLOR;

public class App {
    public static void main(String[] args) {
        AbstractPlayer p1 = new Player("Bernard", TEXT_COLOR(42, 157, 143));
        AbstractPlayer p2 = new DumbAI("Chaosnet");

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
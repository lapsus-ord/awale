package fr.solo.awale;

import fr.solo.awale.ai.SmartAI;

import static com.diogonunes.jcolor.Attribute.TEXT_COLOR;

public class App {
    public static void main(String[] args) {
        Player p1 = new Player("Bernard", TEXT_COLOR(42, 157, 143));
        Player p2 = new SmartAI("Skynet", 2);

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

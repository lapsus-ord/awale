package fr.solo.awale;

import static com.diogonunes.jcolor.Attribute.TEXT_COLOR;

public class App {
    public static void main(String[] args) {
        Player p1 = new Player("Bernard", TEXT_COLOR(42, 157, 143));
        Player p2 = new Player("José", TEXT_COLOR(255, 183, 3));

        int[][] cellCapture = {{1, 4, 2, 2, 4, 0}, {0, 0, 0, 0, 0, 4}};
        int[][] cellBoucle = {{12, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}};
        int[][] cellAffame = {{0, 0, 0, 0, 0, 4}, {2, 0, 0, 0, 6, 0}};
        int[][] cellEnd = {{0,0,0,0,0,1}, {0,0,1,0,0,0}};
        Awale jeu = new Awale(p1, p2);
        p1.joinGame(jeu);
        p2.joinGame(jeu);

        jeu.run();
    }
}

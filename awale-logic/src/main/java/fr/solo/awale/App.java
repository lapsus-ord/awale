package fr.solo.awale;

import static com.diogonunes.jcolor.Attribute.TEXT_COLOR;

public class App {
    public static void main(String[] args) {
        Player p1 = new Player("Bernard", TEXT_COLOR(42, 157, 143));
        Player p2 = new Player("José", TEXT_COLOR(255, 183, 3));

        int[][] cells = {{1, 4, 2, 2, 4, 0}, {0, 0, 0, 0, 0, 4}};
        Board boardPrefait = new Board(cells);
        Awale jeu = new Awale(p1, p2, boardPrefait);
        p1.joinGame(jeu);
        p2.joinGame(jeu);

        System.out.println(jeu);
        jeu.run();
    }
}

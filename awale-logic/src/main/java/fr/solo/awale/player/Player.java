package fr.solo.awale.player;

import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Player extends AbstractPlayer {

    public Player(String username, Attribute colorPlayer) {
        super(username, colorPlayer);
    }

    public Player(AbstractPlayer oldPlayer) {
        super(oldPlayer);
    }

    /**
     * Méthode qui permet à un joueur de voir l'état du jeu et de choisir un trou à jouer.
     *
     * @see Player#play(int)
     */
    public void choose() {
        System.out.println("\nTour de " + colorize(getUsername(), getColor()) + " :");
        Scanner sc = new Scanner(System.in);
        boolean hasPlayed;
        int holeNumber;

        do {
            System.out.print("-> Quel trou jouez-vous ? n°[1, 6] : ");
            holeNumber = sc.nextInt() - 1;
            hasPlayed = play(holeNumber);
        } while (!hasPlayed);
    }

}

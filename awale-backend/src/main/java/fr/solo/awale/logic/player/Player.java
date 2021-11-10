package fr.solo.awale.logic.player;

import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Player extends AbstractPlayer {

    public Player(String username, Attribute colorPlayer) {
        super(username, colorPlayer);
    }

    @Override
    public AbstractPlayer copy() {
        AbstractPlayer newPlayer = new Player(getUsername(), getColor());
        newPlayer.setScore(getScore());
        return newPlayer;
    }

    @Override
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

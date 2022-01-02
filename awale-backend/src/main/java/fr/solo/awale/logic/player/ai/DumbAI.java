package fr.solo.awale.logic.player.ai;

import fr.solo.awale.logic.player.AbstractPlayer;

import java.util.Random;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.GREEN_TEXT;

public class DumbAI extends AbstractPlayer {
    public DumbAI(String username) {
        super(username);
    }

    @Override
    public AbstractPlayer copy() {
        AbstractPlayer newPlayer = new DumbAI(getUsername());
        newPlayer.setScore(getScore());
        return newPlayer;
    }

    @Override
    public void choose() {
        System.out.println("\nTour de " + colorize(getUsername(), getColor()) + " :");
        boolean hasPlayed;
        int holeNumber;

        do {
            System.out.print("-> Quel trou jouez-vous ? n°[1, 6] : ");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            holeNumber = findPlayableHole(); // On cherche le meilleur trou à jouer
            System.out.println(colorize(holeNumber + 1 + "", GREEN_TEXT()));
            hasPlayed = game.playerPlayHisTurn(this, holeNumber);
        } while (!hasPlayed);
    }

    private int findPlayableHole() {
        Random rd = new Random();
        int hole;
        do {
            hole = rd.nextInt(6); // return: 0 <= x < 6
        } while (!game.getBoard().isPlayable(hole, getSide()));
        return hole;
    }
}

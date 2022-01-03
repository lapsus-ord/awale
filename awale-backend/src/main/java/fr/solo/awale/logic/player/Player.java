package fr.solo.awale.logic.player;

import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Player extends AbstractPlayer {
    private boolean hasPlayed;

    public Player(String username) {
        super(username);
        hasPlayed = false;
    }

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
        int holeNumber;

        do {
        } while (!hasPlayed);
        hasPlayed = false;
    }

    public void playedStateTo(boolean state) {
        hasPlayed = state;
    }
}

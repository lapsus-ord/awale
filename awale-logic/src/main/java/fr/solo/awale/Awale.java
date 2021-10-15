package fr.solo.awale;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.RED_TEXT;
import static fr.solo.awale.Awale.Gamestate.*;

public class Awale {
    private Board board;
    private Player player1;
    private Player player2;
    private Player winner;
    private Gamestate state;

    enum Gamestate {
        PlAYER1_TURN, PLAYER2_TURN, START_GAME, END_GAME
    }

    public Awale(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player1.setSide(Side.TOP);
        this.player2 = player2;
        this.player2.setSide(Side.BOTTOM);
        state = Gamestate.START_GAME;
    }

    public Awale(Player player1, Player player2, Board board) {
        this(player1, player2);
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Méthode qui exécute le jeu.<br/>
     * <strong>TODO: Ajouter une condition de fin.</strong>
     */
    public void run() {
        state = PlAYER1_TURN;
        while (!state.equals(END_GAME)) {
            if (state.equals(PlAYER1_TURN)) {
                if (isStarved(player1)) {
                    state = END_GAME;
                    break;
                }
                chooseTrou(player1);
                state = PLAYER2_TURN;
            } else {
                if (isStarved(player2)) {
                    state = END_GAME;
                    break;
                }
                chooseTrou(player2);
                state = PlAYER1_TURN;
            }
        }
        distributionBoardGraine();
        winner = checkWinner();
        System.out.println("\nLe gagnant est " + winner.getUsername() + " avec " + winner.getNbPoint() + " points !!!");
    }

    private boolean isStarved(Player player) {
        return board.getGraineInLine(player.getSide()) == 0;
    }

    private void distributionBoardGraine() {
        player1.addPoints(board.getGraineInLine(player1.getSide()));
        player2.addPoints(board.getGraineInLine(player2.getSide()));
    }

    private Player checkWinner() {
        if (player1.getNbPoint() == player2.getNbPoint())
            return null;
        return player1.getNbPoint() > player2.getNbPoint() ? player1 : player2;
    }

    /**
     * Méthode qui permet à un joueur de voir l'état du jeu et de choisir un trou à jouer.
     *
     * @param player Le joueur qui doit choisir son trou
     */
    private void chooseTrou(Player player) {
        Scanner sc = new Scanner(System.in);
        boolean hasPlayed;
        do {
            System.out.println("\nTour de " + colorize(player.getUsername(), player.getColor()) + " :");
            System.out.print("-> Quel trou jouez-vous ? n°[1, 6] : ");
            int numTrou = sc.nextInt() - 1;
            hasPlayed = player.play(numTrou);
        } while (!hasPlayed);

        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int[] p1Line = board.getLine(player1.getSide());
        int[] p2Line = board.getLine(player2.getSide());
        String playerTop = playerStateOnSide(player1, player2, Side.TOP);
        String playerBottom = playerStateOnSide(player1, player2, Side.BOTTOM);

        str.append("État du jeu :\n");
        str.append("╭———————————————————————————╮\n");
        str.append("|\t");
        // 1ère ligne : On inverse l'affichage pour avoir un cercle (-> de 5 à 0)
        for (int i = p1Line.length - 1; i >= 0; i--) {
            if (p1Line[i] == 0) // S'il n'y a pas de graine on colorie en rouge
                str.append(colorize(p1Line[i] + "", RED_TEXT()));
            else // Sinon la couleur normale du joueur
                str.append(colorize(p1Line[i] + "", player1.getColor()));
            str.append("\t");
        }
        str.append("| ").append(playerTop).append("\n");

        str.append("|\t");
        // 2e ligne : Pas besoin d'inverser le tableau (de 0 à 5)
        for (int j : p2Line) {
            if (j == 0) // S'il n'y a pas de graine on colorie en rouge
                str.append(colorize(j + "", RED_TEXT()));
            else // Sinon la couleur normale du joueur
                str.append(colorize(j + "", player2.getColor()));
            str.append("\t");
        }
        str.append("| ").append(playerBottom).append("\n");

        str.append("╰———————————————————————————╯");
        return str.toString();
    }

    /**
     * @param p1   Un joueur Player
     * @param p2   UN joueur Player
     * @param side Le côté que l'on veut comparer
     * @return Le pseudo et le score du joueur qui est du côté {@code side}
     */
    private String playerStateOnSide(Player p1, Player p2, Side side) {
        if (p1.getSide().equals(side))
            return colorize(p1.getUsername() + "(" + p1.getNbPoint() + ")", p1.getColor());

        return colorize(p2.getUsername() + "(" + p2.getNbPoint() + ")", p2.getColor());
    }
}

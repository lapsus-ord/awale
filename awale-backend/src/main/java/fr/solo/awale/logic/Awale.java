package fr.solo.awale.logic;

import com.diogonunes.jcolor.Attribute;
import fr.solo.awale.logic.player.AbstractPlayer;

import java.util.Arrays;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.RED_TEXT;
import static fr.solo.awale.logic.Awale.Gamestate.*;
import static fr.solo.awale.logic.Side.BOTTOM;
import static fr.solo.awale.logic.Side.TOP;

public class Awale {
    protected Board board;
    protected AbstractPlayer player1;
    protected AbstractPlayer player2;
    protected AbstractPlayer winner;
    protected Gamestate state;

    enum Gamestate {
        PLAYER1_TURN, PLAYER2_TURN, WAITING_GAME, END_GAME
    }

    /**
     * Constructeur avec les joueurs.
     */
    public Awale() {
        this.board = new Board();
        this.player1 = null;
        this.player2 = null;
        state = Gamestate.WAITING_GAME;
        winner = null;
    }

    /**
     * Constructeur avec les joueurs + Un plateau prédéfini.
     */
    public Awale(int[][] board) {
        this();
        this.board = new Board(board);
    }

    /**
     * Constructeur nouveau jeu indépendant
     */
    public Awale(Awale oldGame) {
        this(oldGame.board.getCells());
        oldGame.getPlayer(TOP).copy().joinGame(this);
        oldGame.getPlayer(BOTTOM).copy().joinGame(this);
    }

    /**
     * Méthode qui exécute le jeu.<br/>
     * Commence par changer l'état du jeu en {@code PLAYER1_TURN}.
     */
    public void run() {
        while (player1 == null || player2 == null) {
            System.out.println("En attente de joueurs...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        state = PLAYER1_TURN;
        System.out.println(this);

        // Le jeu tourne tant que l'état du jeu n'est pas END_GAME
        while (!state.equals(END_GAME)) {
            if (state.equals(PLAYER1_TURN)) {
                if (!board.canPlay(player1.getSide())) { // Stop le jeu si le joueur 1 ne peut plus jouer
                    state = END_GAME;
                    break;
                }
                player1.choose();
                System.out.println(this);
                state = PLAYER2_TURN;
            } else {
                if (!board.canPlay(player2.getSide())) { // Stop le jeu si le joueur 2 ne peut plus jouer
                    state = END_GAME;
                    break;
                }
                player2.choose();
                System.out.println(this);
                state = PLAYER1_TURN;
            }
        }

        seedDistribution();
        winner = checkWinner();

        printWinner();
    }

    /**
     * Distribue les graines en fin de partie.
     *
     * @see Awale#run()
     */
    protected void seedDistribution() {
        player1.addPoints(board.getSeedInRow(player1.getSide()));
        player2.addPoints(board.getSeedInRow(player2.getSide()));
        Arrays.fill(board.getCells()[0], 0);
        Arrays.fill(board.getCells()[1], 0);
    }

    /**
     * @return Le joueur gagnant (celui ayant le score le plus élevé).<br/>
     * Ou {@code null} si le jeu finit en égalité.
     * @see Awale#run()
     */
    protected AbstractPlayer checkWinner() {
        if (player1.getScore() == player2.getScore())
            return null;
        return player1.getScore() > player2.getScore() ? player1 : player2;
    }

    /**
     * @param p1   Un joueur Player
     * @param p2   Un joueur Player
     * @param side Le côté que l'on veut comparer
     * @return Le pseudo et le score du joueur qui est du côté {@code side}
     */
    private String playerStateOnSide(AbstractPlayer p1, AbstractPlayer p2, Side side) {
        if (p1.getSide().equals(side))
            return colorize(p1.getUsername() + "(" + p1.getScore() + ")", p1.getColor());

        return colorize(p2.getUsername() + "(" + p2.getScore() + ")", p2.getColor());
    }

    /**
     * Ajoute un joueur à une partie.
     *
     * @param p Le joueur à ajouter.
     */
    public void addPlayer(AbstractPlayer p) {
        if (player1 == null) {
            player1 = p;
            player1.setSide(Side.TOP);
        } else if (player2 == null) {
            player2 = p;
            player2.setSide(Side.BOTTOM);
        }
    }

    /**
     * Affiche sur la console le gagnant du jeu
     */
    private void printWinner() {
        if (winner != null) {
            System.out.println(colorize("\nLe gagnant est " + winner.getUsername() + " avec " + winner.getScore() + " points !!!",
                    Attribute.BRIGHT_MAGENTA_TEXT()));
        } else {
            System.out.println(colorize("\nBravo aux deux joueurs " + player1.getUsername() + " et " + player2.getUsername() + " !\n" +
                    "Le jeu se termine sur une égalité !!! 👏", Attribute.BRIGHT_BLUE_TEXT()));
        }
        System.out.println(this);
    }

    // --- GETTERS ---

    public Board getBoard() {
        return board;
    }

    public AbstractPlayer getPlayer(Side side) {
        return (side.equals(Side.TOP) ? player1 : player2);
    }

    public boolean hasTwoPlayers() {
        return player1 != null && player2 != null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int[] p1Line = board.getRow(player1.getSide());
        int[] p2Line = board.getRow(player2.getSide());
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
}

package fr.solo.awale;

import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.RED_TEXT;
import static fr.solo.awale.Awale.GameState.*;

public class Awale {
    private Board board;
    private Player player1;
    private Player player2;
    private Player winner;
    private GameState state;

    enum GameState {
        PlAYER1_TURN, PLAYER2_TURN, START_GAME, END_GAME
    }


    /**
     * Constructeur avec les joueurs.
     */
    public Awale() {
        this.board = new Board();
        this.player1 = null;
        this.player2 = null;
        state = GameState.START_GAME;
        winner = null;
    }

    /**
     * Constructeur avec les joueurs + Un plateau prédéfini.
     */
    public Awale(int[][] board) {
        this();
        this.board = new Board(board);
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Méthode qui exécute le jeu.<br/>
     * Commence par changer l'état du jeu en {@code PLAYER1_TURN}.
     */
    public void run() throws InterruptedException {
        while (player1 == null || player2 == null) {
            System.out.println("En attente de joueurs...");
            Thread.sleep(10000);
        }
        state = PlAYER1_TURN;
        System.out.println(this);

        // Le jeu tourne tant que l'état du jeu n'est pas END_GAME
        while (!state.equals(END_GAME)) {
            // Tour du joueur 1 (top)
            if (state.equals(PlAYER1_TURN)) {
                if (isStarved(player1) || !board.canPlay(player1.getSide())) {
                    state = END_GAME;
                    break;
                }
                chooseHole(player1);
                state = PLAYER2_TURN;

            } else { // Tour du joueur 2 (bottom)
                if (isStarved(player2) || !board.canPlay(player2.getSide())) {
                    state = END_GAME;
                    break;
                }
                chooseHole(player2);
                state = PlAYER1_TURN;
            }
        }

        seedDistribution();
        winner = checkWinner();

        if (winner != null) {
            System.out.println(colorize("\nLe gagnant est " + winner.getUsername() + " avec " + winner.getScore() + " points !!!",
                    Attribute.BRIGHT_MAGENTA_TEXT()));
        } else {
            System.out.println(colorize("\nBravo aux deux joueurs " + player1.getUsername() + " et " + player2.getUsername() + " !\n" +
                    "Le jeu se termine sur une égalité !!! 👏", Attribute.BRIGHT_BLUE_TEXT()));
        }
    }

    /**
     * Indique si le joueur en face de {@code player} est dans l'état "Affamé".
     *
     * @param player Le joueur choisi.
     * @return {@code true}/{@code false} = Selon si le joueur en face est dans l'état "Affamé".
     * @see Awale#run()
     */
    private boolean isStarved(Player player) {
        return board.getSeedInRow(player.getSide()) == 0;
    }

    /**
     * Distribue les graines en fin de partie.
     *
     * @see Awale#run()
     */
    private void seedDistribution() {
        player1.addPoints(board.getSeedInRow(player1.getSide()));
        player2.addPoints(board.getSeedInRow(player2.getSide()));
    }

    /**
     * @return Le joueur gagnant (celui ayant le score le plus élevé).<br/>
     * Ou {@code null} si le jeu finit en égalité.
     * @see Awale#run()
     */
    private Player checkWinner() {
        if (player1.getScore() == player2.getScore())
            return null;
        return player1.getScore() > player2.getScore() ? player1 : player2;
    }

    /**
     * Méthode qui permet à un joueur de voir l'état du jeu et de choisir un trou à jouer.
     *
     * @param player Le joueur qui doit choisir son trou
     * @see Player#play(int)
     */
    private void chooseHole(Player player) {
        Scanner sc = new Scanner(System.in);
        boolean hasPlayed;
        do {
            System.out.println("\nTour de " + colorize(player.getUsername(), player.getColor()) + " :");
            System.out.print("-> Quel trou jouez-vous ? n°[1, 6] : ");
            int holeNumber = sc.nextInt() - 1;
            hasPlayed = player.play(holeNumber);
        } while (!hasPlayed);

        System.out.println(this);
    }

    /**
     * @param p1   Un joueur Player
     * @param p2   Un joueur Player
     * @param side Le côté que l'on veut comparer
     * @return Le pseudo et le score du joueur qui est du côté {@code side}
     */
    private String playerStateOnSide(Player p1, Player p2, Side side) {
        if (p1.getSide().equals(side))
            return colorize(p1.getUsername() + "(" + p1.getScore() + ")", p1.getColor());

        return colorize(p2.getUsername() + "(" + p2.getScore() + ")", p2.getColor());
    }

    /**
     * Ajoute un joueur à une partie.
     *
     * @param p Le joueur à ajouter.
     */
    public void addPlayer(Player p) {
        if (player1 == null) {
            player1 = p;
            player1.setSide(Side.TOP);
            return;
        }
        if (player2 == null) {
            player2 = p;
            player2.setSide(Side.BOTTOM);
        }
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

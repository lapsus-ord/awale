package fr.solo.awale;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BRIGHT_BLUE_TEXT;
import static com.diogonunes.jcolor.Attribute.BRIGHT_GREEN_TEXT;

public class Awale {
	private Board board;
	private Player player1;
	private Player player2;
	private int nbGrainsTotal;

	public Awale(Board board, Player player1, Player player2) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
	}

	public Board getBoard() {
		return board;
	}

	/**
	 * Méthode qui exécute le jeu.<br/>
	 * <strong>TODO: Ajouter une condition de fin.</strong>
	 */
	public void run() {
		boolean b = true;
		while (true) {
			if (b) {
				chooseTrou(player1);
				b = false;
			} else {
				chooseTrou(player2);
				b = true;
			}
		}
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
			System.out.println("\nTour de " + colorize(player.getUsername(), BRIGHT_BLUE_TEXT()) + " :");
			System.out.print("-> Quel trou jouez-vous ? n°[1, 6] : ");
			int numTrou = sc.nextInt() - 1;
			hasPlayed = player.play(numTrou);
		} while (!hasPlayed);

		System.out.println(this);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		int[] p1Line = board.getLine(0);
		int[] p2Line = board.getLine(1);
		String playerTop = colorize(playerOnSide(player1, player2, Side.TOP), BRIGHT_BLUE_TEXT());
		String playerBottom = colorize(playerOnSide(player1, player2, Side.BOTTOM), BRIGHT_BLUE_TEXT());

		str.append("État du jeu :\n");
		str.append("╭———————————————————————————╮\n");
		str.append("|\t");
		for (int i = p1Line.length - 1; i >= 0; i--)
			str.append(colorize(p1Line[i] + "", BRIGHT_GREEN_TEXT())).append("\t");
		str.append("| ").append(colorize(playerTop, BRIGHT_BLUE_TEXT())).append("\n");

		str.append("|\t");
		for (int j : p2Line)
			str.append(colorize(j + "", BRIGHT_GREEN_TEXT())).append("\t");
		str.append("| ").append(colorize(playerBottom, BRIGHT_BLUE_TEXT())).append("\n");

		str.append("╰———————————————————————————╯");
		return str.toString();
	}

	/**
	 * @param p1   Un joueur Player
	 * @param p2   UN joueur Player
	 * @param side Le côté que l'on veut comparer
	 * @return Le pseudo et le score du joueur qui est du côté {@code side}
	 */
	private String playerOnSide(Player p1, Player p2, Side side) {
		if (p1.getSide().equals(side))
			return p1.getUsername() + "(" + p1.getNbPoint() + ")";

		return p2.getUsername() + "(" + p2.getNbPoint() + ")";
	}
}

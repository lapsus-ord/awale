package fr.solo.awale;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BRIGHT_BLUE_TEXT;
import static com.diogonunes.jcolor.Attribute.BRIGHT_GREEN_TEXT;

public class Awale {
	private final Board board;
	private final Player player1;
	private final Player player2;
	private int nbGrains;

	public Awale(Board board, Player player1, Player player2) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
	}

	public void playTurn(Player player) {
		Scanner sc = new Scanner(System.in);

		System.out.println("\nTour de " + colorize(player.getUsername(), BRIGHT_BLUE_TEXT()) + " :");
		System.out.print("-> Quel trou jouez-vous ? n°[1, 6] : ");
		int numTrou = sc.nextInt() - 1;
		// On limite la portée à l'intervalle disponible [0, 5]
		if (numTrou < 0) numTrou = 0;
		else if (numTrou > 5) numTrou = 5;
		board.play(player1, numTrou);
		System.out.println(this);
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		int[][] cells = board.getCells();
		String playerUsername = colorize(playerOnTop(player1, player2), BRIGHT_BLUE_TEXT());

		str.append("État du jeu :\n");
		str.append("╭———————————————————————————╮\n");
		for (int[] row : cells) {
			str.append("|\t");
			for (int cell : row) {
				str.append(colorize(cell + "", BRIGHT_GREEN_TEXT())).append("\t");
			}
			str.append("| ").append(playerUsername).append("\n");
			playerUsername = colorize(playerOnBottom(player1, player2), BRIGHT_BLUE_TEXT());
		}
		str.append("╰———————————————————————————╯");
		return str.toString();
	}

	private String playerOnTop(Player p1, Player p2) {
		if (p1.getSide().equals(Side.TOP))
			return p1.getUsername() + "(" + p1.getNbPoint() + ")";

		return p2.getUsername() + "(" + p2.getNbPoint() + ")";
	}

	private String playerOnBottom(Player p1, Player p2) {
		if (p1.getSide().equals(Side.BOTTOM))
			return p1.getUsername() + "(" + p1.getNbPoint() + ")";

		return p2.getUsername() + "(" + p2.getNbPoint() + ")";
	}
}

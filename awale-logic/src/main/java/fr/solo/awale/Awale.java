package main.java.fr.solo.awale;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.RED_TEXT;

public class Awale {
	private Board board;
	private Player player1;
	private Player player2;

	public Awale(Player player1, Player player2) {
		this.board = new Board();
		this.player1 = player1;
		this.player1.setSide(Side.TOP);
		this.player2 = player2;
		this.player2.setSide(Side.BOTTOM);
	}

	public Awale(Player player1, Player player2, Board board) {
		this.board = board;
		this.player1 = player1;
		this.player1.setSide(Side.TOP);
		this.player2 = player2;
		this.player2.setSide(Side.BOTTOM);
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
		while (true && !scorePlayerMoreThanHalfGraine()) {
			if(indermination()){
				player1.addPoints(board.getGraineInLine(Side.BOTTOM));
				player2.addPoints(board.getGraineInLine(Side.TOP));
				System.out.println("Fin par indetermination\n");
				break;
			}
			else if (b) {
				if(board.emptyLine(Side.TOP) && !canFeed(player2)){
					player2.addPoints(board.getGraineInLine(Side.BOTTOM));
					System.out.println("Fin par famine\n");
					break;
				}
				chooseTrou(player1);
				b = false;
			} else {
				if(board.emptyLine(Side.BOTTOM) && !canFeed(player1)){
					player2.addPoints(board.getGraineInLine(Side.TOP));
					System.out.println("Fin par famine\n");
					break;
				}
				chooseTrou(player2);
				b = true;
			}
		}
	}

	private boolean indermination(){
		int[] line1 = board.getLine(Side.TOP);
		int[] line2 = board.getLine(Side.BOTTOM);
		return inderminationAux(line1, line2, 0, 0);
	}

	private boolean inderminationAux(int[] l, int[] l2, int i, int j){
		if(i==6 || j==6)return false;
		else if(l[i]==0)return inderminationAux(l,l2,i++,j);
		else if(l2[j]==0)return inderminationAux(l,l2,i,j++);
		return (l2[j]==2||l2[j]==3) && board.getDistance(i, j)>=l[i] && inderminationAux(l, l2, i++, j++);
	}

	private boolean canFeed(Player p) {
		int[] t = board.getLine(p.getSide());
		int i = 0;
		while(i < t.length) {
			if(t[i]>t.length-1-i) return true;
		}
		return false;
	}

	private boolean scorePlayerMoreThanHalfGraine(){
		return Math.max(player1.getNbPoint(), player2.getNbPoint()) >=25;
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

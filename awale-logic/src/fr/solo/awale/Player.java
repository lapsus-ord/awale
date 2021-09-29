package fr.solo.awale;

import java.util.Arrays;

public class Player {
	private String username;
	private int nbPoint;
	private Side side;
	private Awale jeu;

	/**
	 * @param username pseudo du joueur.
	 * @param side     côté joueur sur le plateau.<br/>
	 *                 false/true = haut/bas
	 */
	public Player(String username, Side side) {
		this.username = username;
		this.side = side;
		nbPoint = 0;
	}

	public String getUsername() {
		return username;
	}

	public int getNbPoint() {
		return nbPoint;
	}

	public Side getSide() {
		return side;
	}

	public void setJeu(Awale jeu) {
		this.jeu = jeu;
	}

	/**
	 * @return {@code true} = S'il reste des graines à déplacer ; {@code false} = Si le joueur ne peut plus rien déplacer.
	 */
	public boolean canPlay() {
		int indexSide = side.equals(Side.TOP) ? 0 : 1;
		int[] linePlayer = jeu.getBoard().getLine(indexSide);

		boolean isWholeLineEmpty = Arrays.stream(linePlayer).allMatch(i -> i == 0);
		return !isWholeLineEmpty;
	}

	/**
	 * Joue le coup d'un joueur.
	 *
	 * @param trouSrc Le trou d'origine (du côté du joueur)
	 * @return {@code true} = coup joué sans soucis ;<br/>
	 * {@code false} = le joueur n'a pas pu jouer le coup
	 */
	public boolean play(int trouSrc) {
		Board board = jeu.getBoard();
		// System.out.println("side de " + username + " : " + side);
		int indexSidePlayer = side.equals(Side.TOP) ? 0 : 1;
		int indexSideEnemy = indexSidePlayer == 0 ? 1 : 0;

		int inHand = board.viderTrou(indexSidePlayer, trouSrc);
		// Si le trou est vide le joueur ne peut pas jouer ce coup
		if (inHand == 0) return false;

		// Distribution des graines en main sur notre plateau
		for (int i = trouSrc + 1; i <= 5 && inHand > 0; i++) {
			/* System.out.print("ligne=" + indexSidePlayer + " | ");
			System.out.println("Ajout graine joueur (i=" + (i + 1) + ")");*/
			board.addGraine(indexSidePlayer, i);
			inHand--;
		}

		// Puis, s'il en reste, on distribue sur l'autre partie du plateau
		for (int j = 0; j <= 5 && inHand > 0; j++) {
			/*System.out.print("ligne=" + indexSideEnemy + " | ");
			System.out.println("Ajout graine ennemi (j=" + (j + 1) + ")");*/
			board.addGraine(indexSideEnemy, j);
			inHand--;
		}

		return true;
	}
}

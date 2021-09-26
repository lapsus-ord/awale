package fr.solo.awale;

import java.util.Arrays;

public class Board {
	private final int[][] cells;

	public Board() {
		cells = new int[2][6];
		fillBoard();
	}

	private void fillBoard() {
		Arrays.fill(cells[0], 4);
		Arrays.fill(cells[1], 4);
	}

	public int[][] getCells() {
		return cells;
	}

	/**
	 * @param side Le côté du plateau (ou la ligne), i∈[0, 1]
	 * @param trou Le numéro du trou (ou la colonne), j∈[0, 5]
	 * @return Le nombre de graines dans le trou
	 */
	public int getTrou(int side, int trou) {
		return cells[side][trou];
	}

	private int viderTrou(int side, int trou) {
		int stock = cells[side][trou];
		cells[side][trou] = 0;
		return stock;
	}

	/**
	 * Ajoute une graine dans un trou.
	 *
	 * @param side Le côté du plateau (ou la ligne), i∈[0, 1]
	 * @param trou Le numéro du trou (ou la colonne), j∈[0, 5]
	 * @see Board#play(Player, int)
	 */
	private void ajoutGraine(int side, int trou) {
		cells[side][trou]++;
	}

	/**
	 * Joue le coup d'un joueur.
	 *
	 * @param trouSrc Le trou d'origine (du côté du joueur)
	 * @param player  Quel joueur joue le coup
	 * @return {@code true} = coup joué sans soucis ;<br/>
	 * {@code false} = le joueur n'a pas pu jouer le coup
	 */
	public boolean play(Player player, int trouSrc) {
		int sideOfPlayer = player.isOnTop() ? 1 : 0;
		int sideOfEnemy = player.isOnBottom() ? 1 : 0;

		int inHand = viderTrou(sideOfPlayer, trouSrc);
		// Si le trou est vide le joueur ne peut pas jouer ce coup
		if (inHand == 0) return false;

		// On répartit les graines en main sur notre plateau
		for (int i = trouSrc + 1; i <= 5 && inHand > 0; i++) {
			ajoutGraine(sideOfPlayer, i);
			inHand--;
		}

		// Puis, s'il en reste, on répartit sur l'autre partie du plateau
		for (int j = 5; j >= 0 && inHand > 0; j--) {
			ajoutGraine(sideOfEnemy, j);
			inHand--;
		}

		return true;
	}
}

package fr.solo.awale;

import java.util.Arrays;

public class Board {
	private int[][] cells;

	public Board() {
		cells = new int[2][6];
		initBoard();
	}

	private void initBoard() {
		Arrays.fill(cells[0], 4);
		Arrays.fill(cells[1], 4);
	}

	/**
	 * @param nb {@code 0} = 1ère ligne ; {@code 1} = 2e ligne
	 * @return Un tableau contenant les trous du côté de {@code nb}
	 */
	public int[] getLine(int nb) {
		return cells[nb];
	}

	/**
	 * @param side Le côté du plateau (ou la ligne), i∈[0, 1]
	 * @param trou Le numéro du trou (ou la colonne), j∈[0, 5]
	 * @return Le nombre de graines dans le trou
	 */
	public int getTrou(int side, int trou) {
		return cells[side][trou];
	}

	/**
	 * Vide le trou passé en paramètre.
	 * @param side Le côté du plateau (ou la ligne), i∈[0, 1]
	 * @param trou Le numéro du trou (ou la colonne), j∈[0, 5]
	 * @return Le nombre de graines dans le trou
	 */
	public int viderTrou(int side, int trou) {
		int stock = cells[side][trou];
		cells[side][trou] = 0;
		return stock;
	}

	/**
	 * Ajoute une graine dans un trou.
	 *
	 * @param side Le côté du plateau (ou la ligne), i∈[0, 1]
	 * @param trou Le numéro du trou (ou la colonne), j∈[0, 5]
	 * @see Player#play(int)
	 */
	public void addGraine(int side, int trou) {
		cells[side][trou]++;
	}
}

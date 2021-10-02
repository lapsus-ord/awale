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
	 * @param side {@code TOP} = 1ère ligne ; {@code BOTTOM} = 2e ligne
	 * @return Un tableau contenant les trous du côté de {@code nb}
	 */
	public int[] getLine(Side side) {
		 if (side.equals(Side.TOP))
		 	return cells[0];
		return cells[1];
	}

	/**
	 * @param side Le côté du plateau (ou la ligne), i∈[0, 1]
	 * @param trou Le numéro du trou (ou la colonne), j∈[0, 5]
	 * @return Le nombre de graines dans le trou
	 */
	private int getTrou(int side, int trou) {
		return cells[side][trou];
	}

	/**
	 * Vide le trou passé en paramètre.
	 * @param side Le côté du plateau (ou la ligne), i∈[0, 1]
	 * @param trou Le numéro du trou (ou la colonne), j∈[0, 5]
	 * @return Le nombre de graines dans le trou
	 */
	public int clearTrou(Side side, int trou) {
		int stock = getLine(side)[trou];
		getLine(side)[trou] = 0;
		return stock;
	}

	/**
	 * Ajoute une graine dans un trou.
	 *
	 * @param side Le côté du plateau (ou la ligne), i∈[0, 1]
	 * @param trou Le numéro du trou (ou la colonne), j∈[0, 5]
	 * @see Player#play(int)
	 */
	public void addGraine(Side side, int trou) {
		getLine(side)[trou]++;
	}


	public int ramasser(Side side, int trou){
		if (trou < 0){
			return 0;
		}
		else if (getLine(side)[trou] == 2 || getLine(side)[trou] == 3) {
			int nbgraine = getLine(side)[trou];
			clearTrou(side, trou);
			if (trou == 0){
				return nbgraine;
			}else{
				return nbgraine + rafle(side,trou - 1);
			}
		}
		else {
			return 0;
		}
	}

	public int rafle(Side side, int trou){
		while (getLine(side)[trou]>= 0){
			return ramasser(side,trou - 1);
		}
		return 0;
	}
}

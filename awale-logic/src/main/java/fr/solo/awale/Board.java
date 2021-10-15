package fr.solo.awale;

import java.util.Arrays;

public class Board {
    private int[][] cells;
    private int nbGraines;

    public Board() {
        cells = new int[2][6];
        nbGraines = 48;
        initBoard();
    }

    public Board(int[][] cells) {
        this.cells = cells;
        nbGraines = 48;
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

    public boolean isPlayable(int trou, Side side) {
        if (getLine(side)[trou] == 0)
            return false;
        if (getGraineInLine(getOppositeSide(side)) == 0) { // Cas Affamé
            return (getLine(side)[trou] >= (cells[0].length - trou));
        }
        return true;
    }

    public Side getOppositeSide(Side side) {
        return side.equals(Side.TOP) ? Side.BOTTOM : Side.TOP;
    }

    /**
     * Vide le trou passé en paramètre.
     *
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

    /**
     * Ramasser les graines dans un trou (comprend la rafle).
     *
     * @param side Le côté du plateau (ou la ligne), i∈[0, 1]
     * @param trou Le numéro du trou (ou la colonne), j∈[0, 5]
     * @return Le nombre de graine récupéré par le ramassage.
     */
    public int ramasser(Side side, int trou) {
        if (getLine(side)[trou] == 2 || getLine(side)[trou] == 3) {
            if (trou == 0) {
                return clearTrou(side, trou);
            } else {
                return clearTrou(side, trou) + ramasser(side, trou - 1);
            }
        } else {
            return 0;
        }
    }

    public int getGraineInLine(Side s) {
        int[] t = getLine(s);
        int n = 0;
        for (int j : t) {
            if (j != 0) {
                n += j;
            }
        }
        return n;
    }

    public void removeNbGraine(int ramassage) {
        this.nbGraines -= ramassage;
    }
}

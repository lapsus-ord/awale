package fr.solo.awale;

import java.util.Arrays;

public class Board {
    private int[][] cells;
    private int nbOfSeeds;

    public Board() {
        cells = new int[2][6];
        nbOfSeeds = 48;
        initBoard();
    }

    public Board(int[][] board) {
        this.cells = board;
        nbOfSeeds = 48;
    }

    // Remplit le plateau de graines
    private void initBoard() {
        Arrays.fill(cells[0], 4);
        Arrays.fill(cells[1], 4);
    }

    /**
     * @param side {@code TOP} = 1ère ligne ; {@code BOTTOM} = 2e ligne
     * @return Un tableau contenant les trous du côté de {@code nb}
     */
    public int[] getRow(Side side) {
        if (side.equals(Side.TOP))
            return cells[0];
        return cells[1];
    }

    /**
     * Indique si un trou peut être joué.
     *
     * @param hole Le trou choisi.
     * @param side Le côté du plateau choisi.
     * @return {@code true}/{@code false} = Selon si le trou peut être joué.
     */
    public boolean isPlayable(int hole, Side side) {
        if (getRow(side)[hole] == 0)
            return false;
        if (getSeedInRow(getOppositeSide(side)) == 0) { // Cas Affamé :
            // Renvoie true si on peut utiliser ce trou pour distribuer chez l'ennemi
            // Debug temp :
            System.out.println("affamé");
            System.out.println("- " + getRow(side)[hole] + " vs " + (cells[0].length - hole));
            // Peut-être rajouter une condition pour savoir si on a vraiment besoin de Affamé...
            return (getRow(side)[hole] >= (cells[0].length - hole));
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
     * @param hole Le numéro du trou (ou la colonne), j∈[0, 5]
     * @return Le nombre de graines dans le trou
     */
    public int clearHole(Side side, int hole) {
        int stock = getRow(side)[hole];
        getRow(side)[hole] = 0;
        return stock;
    }

    /**
     * Ajoute une graine dans un trou.
     *
     * @param side Le côté du plateau (ou la ligne), i∈[0, 1]
     * @param hole Le numéro du trou (ou la colonne), j∈[0, 5]
     * @see Player#play(int)
     */
    public void addSeed(Side side, int hole) {
        getRow(side)[hole]++;
    }

    /**
     * Ramasser les graines dans un trou (comprend la rafle).
     *
     * @param side Le côté du plateau (ou la ligne), i∈[0, 1]
     * @param hole Le numéro du trou (ou la colonne), j∈[0, 5]
     * @return Le nombre de graine récupéré par le ramassage.
     * @see Player#captureSeed(Board, int)
     */
    public int capturing(Side side, int hole) {
        if (getRow(side)[hole] == 2 || getRow(side)[hole] == 3) {
            if (hole == 0) {
                return clearHole(side, hole);
            } else {
                return clearHole(side, hole) + capturing(side, hole - 1);
            }
        } else {
            return 0;
        }
    }

    /**
     * @param s Le côté du plateau.
     * @return Renvoie le nombre de graines dans une ligne.
     */
    public int getSeedInRow(Side s) {
        int[] t = getRow(s);
        int n = 0;
        for (int j : t) {
            if (j != 0) {
                n += j;
            }
        }
        return n;
    }

    /**
     * Enlève un nombre de graines du nombre de graines sur le plateau.
     *
     * @param nb Le nombre de graines capturées.
     */
    public void removeNbSeed(int nb) {
        this.nbOfSeeds -= nb;
    }
}

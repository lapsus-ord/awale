package main.java.fr.solo.awale;

import java.util.Arrays;

public class Board {
    private int[][] cells;

    public Board() {
        cells = new int[2][6];
        initBoard();
    }

    public Board(int[][] cells) {
        this.cells = cells;
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
     *
     * @param trou le numéro du trou entre 1 et 6
     * @param trou1 le numéro du trou entre 1 et 6
     * @return distance entre les 2 trous
     */
    public int getDistance(int trou, int trou1){
        return Math.abs(trou+trou1+2)-1;
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

    public boolean emptyLine(Side s){
        int[] t = getLine(s);
        for(int i = 0; i < t.length; i++){
            if(t[i]!=0){
                return false;
            }
        }
        return true;
    }

    public int getGraineInLine(Side s){
        int[] t = getLine(s);
        int n = 0;
        for(int i = 0; i < t.length; i++){
            if(t[i]!=0){
                n += t[i];
            }
        }
        return n;
    }



}

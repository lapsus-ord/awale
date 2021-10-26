package fr.solo.awale;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Config {
    private Awale game;
    private List<Config> children;
    private int holePlayed;
    private Side ourSide;
    private Side originSide;

    public Config(Awale game, Side configSide, int order) {
        this.game = new Awale(game.getBoard().getCells());
        this.children = new ArrayList<>(6);
        this.ourSide = configSide;
        generateChildren(order);
    }

    public Config(Awale game, Side configSide, Side originSide) {
        this(game, configSide, -1);
        this.originSide = originSide;
    }

    private void generateChildren(int order) { // order doit décroitre au fur et à mesure
        for (int i = 0; i < 6; i++)
            children.add(new Config(game, game.getBoard().getOppositeSide(ourSide))); // Ajouter une condition de fin pour avoir des feuilles
    }

    public int eval() {
        int configScore = game.getScorePlayer(ourSide);
        int enemyScore = game.getScorePlayer(game.getBoard().getOppositeSide(ourSide));

        // Comparaison des coups des deux joueurs
        int eval = 0; // Si la diff entre les scores est de 0
        if (enemyScore - configScore >= 4) eval = 4; // Une rafle pour le joueur
        else if (Math.abs(enemyScore - configScore) == 3) eval = 3; // 3 graines ramassées
        else if (Math.abs(enemyScore - configScore) == 2) eval = 2; // 2 graines ramassées
        else if (Math.abs(enemyScore - configScore) == 1) eval = 1; // 2 graines ramassées

        // --- MIN-MAX ---
        if (ourSide.equals(originSide))
            return eval;
        // Pas du même côté que l'origine
        return -eval;
    }

    /**
     * Renvoie le trou à jouer selon le score des enfants
     */
    public int chooseTheBestChildren() {
        Comparator<Config> evalComparator = Comparator.comparingInt(Config::eval);
        Optional<Config> bestChild = children.stream().max(evalComparator);
        return bestChild.map(config -> config.holePlayed).orElse(Integer.MIN_VALUE);
    }
}

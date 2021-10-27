package fr.solo.awale;

import java.util.*;

public class Config {
    private Awale game;
    private List<Config> children;
    private int holePlayed;
    private int bestHolePlayed;
    private Side ourSide;
    private Side originSide;

    /**
     * Constructeur pour les enfants des Configs
     */
    public Config(Awale game, Side configSide, int order) {
        this.game = new Awale(game.getBoard().getCells(),
                game.getPlayer(Side.TOP),
                game.getPlayer(Side.BOTTOM));
        this.children = new ArrayList<>(6);
        this.ourSide = configSide;
        generateChildren(order);
    }

    /**
     * Constructeur appelé par SmartAI
     */
    public Config(Awale game, Side configSide, Side originSide, int order) {
        this(game, configSide, order);
        this.originSide = originSide;
    }

    private void generateChildren(int order) {
        if (order > 0) {
            for (int i = 0; i < 6; i++) {
                if (game.getBoard().isPlayable(i, ourSide)) {
                    children.add(new Config(game, game.getBoard().getOppositeSide(ourSide), --order));
                }
            }
        }
        chooseTheBestChildren();
    }

    public int eval() {
        int configScore = game.getScorePlayer(ourSide);
        int enemyScore = game.getScorePlayer(game.getBoard().getOppositeSide(ourSide));

        // Comparaison des coups des deux joueurs
        int eval = 0; // Si la diff entre les scores est de 0
        if (enemyScore - configScore >= 4) eval = 4; // Une rafle pour le joueur
        else if (enemyScore - configScore == 3) eval = 3; // 3 graines ramassées
        else if (enemyScore - configScore == 2) eval = 2; // 2 graines ramassées
        else if (enemyScore - configScore == 1) eval = 1; // 2 graines ramassées

        // --- MIN-MAX ---
        if (ourSide.equals(originSide))
            return eval;
        // Pas du même côté que l'origine
        return -eval;
    }

    /**
     * Renvoie le trou à jouer selon le score des enfants
     */
    public void chooseTheBestChildren() {
        //Comparator<Config> evalComparator = Comparator.comparingInt(Config::eval);
        //Optional<Config> bestChild = children.stream().max(evalComparator);
        //bestHolePlayed = bestChild.map(config -> config.bestHolePlayed).orElse(Integer.MIN_VALUE);
        bestHolePlayed = (int) ((Math.random() * (6-1)) + 1);
    }

    public int getBestHolePlayed() {
        return bestHolePlayed;
    }
}

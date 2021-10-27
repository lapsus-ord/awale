package fr.solo.awale;

import java.util.*;

public class SmartAI extends AI {
    private List<Config> children;
    private int horizon;

    public SmartAI(String username, int horizon) {
        super(username);
        children = new ArrayList<>(6);
        this.horizon = horizon;
    }

    private void generateTree() {
        for (int i = 0; i < 6; i++) {
            if (game.getBoard().isPlayable(i, getSide())) {
                children.add(new Config(super.game, getSide(), getSide(), horizon));
            }
        }
    }

    // Renvoie le trou à jouer selon le score des enfants
    public int chooseTheBestChildren() {
        generateTree();
        Comparator<Config> evalComparator = Comparator.comparingInt(Config::eval);

        Optional<Config> bestChild = children.stream().max(evalComparator);
        for (Config child : children) {
            System.out.println(" bh=" + child.getBestHolePlayed());
        }
        return bestChild.map(Config::getBestHolePlayed).orElse(Integer.MIN_VALUE);
    }
}

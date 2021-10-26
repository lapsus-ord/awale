package fr.solo.awale;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class smartAI extends AI {
    private List<Config> children;

    public smartAI(String username) {
        super(username);
        children = new ArrayList<>(6);
    }

    public void generateTree() {
        for (int i = 0; i < 6; i++)
            children.add(new Config(super.game, getSide(), getSide()));
    }

    // Renvoie le trou à jouer selon le score des enfants
    public int chooseTheBestChildren() {
        Comparator<Config> evalComparator = Comparator.comparingInt(Config::eval);
        Optional<Config> bestChild = children.stream().max(evalComparator);
        bestChild.map(config -> config.holePlayed).orElse(Integer.MIN_VALUE);
        return 0;
    }
}

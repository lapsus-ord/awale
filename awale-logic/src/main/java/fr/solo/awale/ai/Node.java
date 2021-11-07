package fr.solo.awale.ai;

import fr.solo.awale.Awale;
import fr.solo.awale.Side;

import java.util.ArrayList;
import java.util.List;

import static fr.solo.awale.Side.BOTTOM;
import static fr.solo.awale.Side.TOP;

public class Node {
    private Awale game;
    private boolean isMaxPlayer;
    private int holePlayed;
    private List<Node> children;

    /**
     * Constructeur pour le root<br>
     * Créé un jeu indépendant de son parent et a une valeur min/max
     */
    public Node(Awale oldGame, boolean isMaxPlayer) {
        game = new Awale(oldGame);
        this.isMaxPlayer = isMaxPlayer;
        children = new ArrayList<>();
    }

    /**
     * Constructeur des nœuds de l'arbre
     */
    public Node(Awale oldGame, boolean isMaxPlayer, int holePlayed) {
        this(oldGame, isMaxPlayer);
        this.holePlayed = holePlayed;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public int eval() {
        // Quand on est une feuille, on retourne l'état du plateau
        if (children.isEmpty()) {
            int ourScore = game.getPlayer(getOurSide()).getScore();
            int enemyScore = game.getPlayer(getOppositeSide()).getScore();
            return ourScore - enemyScore;
        }

        int value;
        if (isMaxPlayer) {  // Si on est en MAX, on retourne la meilleure eval() des enfants
            value = Integer.MIN_VALUE;
            for (Node child : children)
                value = Math.max(value, child.eval());

        } else {            // Si on est en MIN, on retourne la pire eval() des enfants
            value = Integer.MAX_VALUE;
            for (Node child : children)
                value = Math.min(value, child.eval());
        }

        return value;
    }

    /**
     * Le Node joue son coup
     */
    public void play() {
        game.getPlayer(getOurSide()).play(holePlayed);
    }

    public List<Node> getChildren() {
        return children;
    }

    public int getHolePlayed() {
        return holePlayed;
    }

    public Awale getGame() {
        return game;
    }

    public Side getOurSide() {
        // On considère que notre IA sera toujours à la position du 2e joueur
        return isMaxPlayer ? BOTTOM : TOP;
    }

    public Side getOppositeSide() {
        return game.getBoard().getOppositeSide(getOurSide());
    }

    @Override
    public String toString() {
        if (children.isEmpty()) {
            return "(" + holePlayed + ")\n";
        }

        StringBuilder str = new StringBuilder();
        for (Node child : children) {
            str.append("|\t").append(child);
        }
        return "|\t[" + holePlayed + "]\n" + str;
    }
}

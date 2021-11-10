package fr.solo.awale.logic.player.ai;

import fr.solo.awale.logic.Awale;
import fr.solo.awale.logic.Side;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static fr.solo.awale.logic.Side.BOTTOM;
import static fr.solo.awale.logic.Side.TOP;

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

    /**
     * @return Le score de la meilleure branche.
     */
    private int eval() {
        // Quand on est une feuille, Le score du plateau (ici la différence des scores)
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
     * Le Node joue son coup.
     *
     * @see SmartAI#generateTree(Node, boolean, int)
     */
    public void play() {
        game.getPlayer(getOurSide()).play(holePlayed);
    }

    /**
     * On utilise cette méthode surtout sur les enfants directes du nœud {@code root}
     *
     * @return Le meilleur trou à jouer (sinon une exception).
     */
    public int findBestHole() {
        // DEBUG : Affichage de l'état des evals des enfants du nœud
        children.forEach(child -> System.out.println("Trou n°" + (child.getHolePlayed() + 1) + " eval = " + child.eval()));

        return children.stream()
                .max(Comparator.comparingInt(Node::eval))
                .map(Node::getHolePlayed)
                .orElseThrow(NoSuchElementException::new);
    }

    // --- GETTERS/SETTERS ---

    public void addChild(Node child) {
        children.add(child);
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

package fr.solo.awale.ai;

import fr.solo.awale.Awale;
import fr.solo.awale.Side;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static fr.solo.awale.Side.*;

public class Node {
    private Awale game;
    private Side ourSide;
    private int holePlayed;
    private List<Node> children;

    /**
     * Constructeur pour le root
     */
    public Node(Awale game, Side side) {
        this.game = new Awale(game.getBoard().getCells(),
                game.getPlayer(Side.TOP), game.getPlayer(BOTTOM));

        ourSide = side;
        children = new ArrayList<>();
    }

    public Node(Awale oldGame, Side side, int holePlayed) {
        this(oldGame, side);
        this.holePlayed = holePlayed;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public int eval() {
        if (children.isEmpty()) {
            return 0;
        }
        int configScore = game.getPlayer(ourSide).getScore();
        int enemyScore = game.getPlayer(game.getBoard().getOppositeSide(ourSide)).getScore();

        // Comparaison des coups des deux joueurs
        int eval = 0; // Si la diff entre les scores est de 0
        if (enemyScore - configScore >= 4) eval = 4; // Une rafle pour le joueur
        else if (enemyScore - configScore == 3) eval = 3; // 3 graines ramassées
        else if (enemyScore - configScore == 2) eval = 2; // 2 graines ramassées
        else if (enemyScore - configScore == 1) eval = 1; // 2 graines ramassées

        Comparator<Node> evalComparator = Comparator.comparing(Node::eval);
        Node bestChild = children.stream()
                .max(ourSide.equals(BOTTOM) ? evalComparator : evalComparator.reversed())
                .orElseThrow(NoSuchElementException::new);

        return eval + bestChild.eval();
    }

    public List<Node> getChildren() {
        return children;
    }

    public int getHolePlayed() {
        return holePlayed;
    }

    public Side getSide() {
        return ourSide;
    }

    public Awale getGame() {
        return game;
    }

    public void play() {
        // Le Node joue son coup
        game.getPlayer(ourSide).play(holePlayed);
    }

    @Override
    public String toString() {
        return "trou(" + holePlayed + ") : " + ourSide;
    }
}

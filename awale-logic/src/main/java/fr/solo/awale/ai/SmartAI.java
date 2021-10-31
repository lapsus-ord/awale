package fr.solo.awale.ai;

import fr.solo.awale.Board;
import fr.solo.awale.Side;

import java.util.Comparator;
import java.util.Optional;

public class SmartAI extends AI {
    private Node root;
    private int horizon;

    public SmartAI(String username, int horizon) {
        super(username);
        this.horizon = horizon;
    }

    private void generateTree(Node parentNode, boolean isMaxPlayer, int order) {
        parentNode.play();
        Board boardParentNode = parentNode.getGame().getBoard();
        Side childSide = isMaxPlayer ? Side.TOP : Side.BOTTOM;

        for (int i = 0; i < 6; i++) {
            System.out.println("trou n°" + i + " à l'horizon " + order);
            if (boardParentNode.isPlayable(i, childSide)) {
                Node newNode = new Node(parentNode.getGame(), childSide, i);
                parentNode.addChild(newNode);

                /*if (order > 0) {
                    generateTree(newNode, !isMaxPlayer, order--);
                }*/
            }
        }
    }

    // Renvoie le trou à jouer selon le score des enfants
    public int findBestChildren() {
        root = new Node(game, getSide());
        generateTree(root, true, horizon);

        /*Optional<Node> bestChild = root.getChildren().stream()
                .max(Comparator.comparingInt(Node::eval));

        int childNb = 0;
        for (Node child : root.getChildren()) {
            System.out.println("Enfant n°" + childNb + " eval = " + child.eval());
            childNb++;
        }
        return bestChild.map(Node::getHolePlayed).orElse(-42);*/
        return 1;
    }
}

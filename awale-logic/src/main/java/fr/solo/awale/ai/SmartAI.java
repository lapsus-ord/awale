package fr.solo.awale.ai;

import fr.solo.awale.Board;
import fr.solo.awale.Side;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.GREEN_TEXT;

public class SmartAI extends AI {
    private Node root;
    private int horizon;

    public SmartAI(String username, int horizon) {
        super(username);
        this.horizon = horizon;
    }

    @Override
    public void choose() {
        boolean hasPlayed;
        int holeNumber;
        System.out.println("\nTour de " + colorize(getUsername(), getColor()) + " :");

        do {
            holeNumber = findBestChildren();
            System.out.print("-> Quel trou jouez-vous ? n°[1, 6] : ");
            System.out.println(colorize(holeNumber + 1 + "", GREEN_TEXT()));
            hasPlayed = play(holeNumber);
        } while (!hasPlayed);
    }

    private void generateTree(Node parentNode, boolean isMaxPlayer, int order) {
        parentNode.play();
        Board parentNodeBoard = parentNode.getGame().getBoard();
        Side childSide = isMaxPlayer ? Side.TOP : Side.BOTTOM;

        for (int i = 0; i < 6; i++) {
            if (parentNodeBoard.isPlayable(i, childSide)) {
                Node newNode = new Node(parentNode.getGame(), childSide, i);
                parentNode.addChild(newNode);

                if (order > 0) {
                    generateTree(newNode, !isMaxPlayer, order - 1);
                }
            }
        }
    }

    /**
     * Renvoie le trou à jouer selon le score des enfants
     */
    public int findBestChildren() {
        root = new Node(game, getSide());
        generateTree(root, true, horizon);

        /*System.out.println("\n");
        boolean[] flag = new boolean[6];
        Arrays.fill(flag, true);
        toString(root, flag, 0, false);
        System.out.println("\n");*/

        Optional<Node> bestChild = root.getChildren().stream()
                .max(Comparator.comparingInt(Node::eval));
        return bestChild.map(Node::getHolePlayed)
                .orElseThrow(NoSuchElementException::new);
    }

    private static void toString(Node x, boolean[] flag, int depth, boolean isLast) {
        // Condition when node is None
        if (x == null)
            return;

        // Loop to print the depths of the current node
        for (int i = 1; i < depth; ++i) {

            // Condition when the depth is exploring
            if (flag[i]) {
                System.out.print("| " + " " + " " + " ");
            }

            // Otherwise, print the blank spaces
            else {
                System.out.print(" " + " " + " " + " ");
            }
        }

        // Condition when the current node is the root node
        if (depth == 0) {
            System.out.println("root(" + x.getHolePlayed() + ")");
        } else {
            System.out.print("|\t  (" + x.getHolePlayed() + ")\n");
        }

        int it = 0;
        for (Node i : x.getChildren()) {
            ++it;

            // Recursive call for the
            // children nodes
            toString(i, flag, depth + 1, it == (x.getChildren().size()) - 1);
        }
        flag[depth] = true;
    }
}

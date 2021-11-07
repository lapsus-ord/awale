package fr.solo.awale.ai;

import fr.solo.awale.Player;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.GREEN_TEXT;

public class SmartAI extends Player {
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

    private void generateTree(Node parentNode, boolean isMaxPlayer, int depth) {
        parentNode.play();

        for (int i = 0; i < 6; i++) {
            if (parentNode.getGame().getBoard().isPlayable(i, parentNode.getOppositeSide())) {
                Node newNode = new Node(parentNode.getGame(), isMaxPlayer, i);
                parentNode.addChild(newNode);

                if (depth > 0) {
                    generateTree(newNode, !isMaxPlayer, depth - 1);
                }
            }
        }
    }

    /**
     * Renvoie le trou à jouer selon le score des enfants
     */
    public int findBestChildren() {
        Node root = new Node(game, true);
        for (int i = 0; i < 6; i++) {
            if (root.getGame().getBoard().isPlayable(i, root.getOurSide())) {
                root.addChild(new Node(root.getGame(), true, i));
            }
        }
        for (Node child : root.getChildren()) {
            generateTree(child, false, horizon - 1);
        }

        Optional<Node> bestChild = root.getChildren().stream()
                .max(Comparator.comparingInt(Node::eval));

        for (Node child : root.getChildren()) {
            System.out.println("Trou n°" + (child.getHolePlayed() + 1) + " eval = " + child.eval());
        }
        return bestChild.map(Node::getHolePlayed)
                .orElseThrow(NoSuchElementException::new);
    }
}

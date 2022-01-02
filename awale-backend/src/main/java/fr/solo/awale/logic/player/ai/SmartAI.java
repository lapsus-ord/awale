package fr.solo.awale.logic.player.ai;

import fr.solo.awale.logic.player.AbstractPlayer;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.GREEN_TEXT;

public class SmartAI extends AbstractPlayer {
    private Node root;
    private int horizon;

    public SmartAI(String username, int horizon) {
        super(username);
        this.horizon = horizon;
    }

    @Override
    public AbstractPlayer copy() {
        AbstractPlayer newPlayer = new SmartAI(getUsername(), horizon);
        newPlayer.setScore(getScore());
        return newPlayer;
    }

    @Override
    public void choose() {
        init();
        System.out.println("\nTour de " + colorize(getUsername(), getColor()) + " :");
        boolean hasPlayed;
        int holeNumber;

        do {
            holeNumber = root.findBestHole(); // On cherche le meilleur trou à jouer
            System.out.print("-> Quel trou jouez-vous ? n°[1, 6] : ");
            System.out.println(colorize(holeNumber + 1 + "", GREEN_TEXT()));
            hasPlayed = game.playerPlayHisTurn(this, holeNumber);
        } while (!hasPlayed);
    }

    /**
     * Création du nœud root (notre état actuel) et de ses enfants (les coups que l'on peut jouer).
     */
    private void init() {
        // L'état du jeu sans que nous ayons encore joué
        root = new Node(game, true);

        // Premiers enfants de notre état (= les coups possibles que l'on peut jouer)
        for (int i = 0; i < 6; i++) {
            if (root.getGame().getBoard().isPlayable(i, root.getOurSide())) {
                root.addChild(new Node(root.getGame(), true, i));
            }
        }

        // On génère enfin les arbres de chacun de nos enfants
        for (Node child : root.getChildren())
            generateTree(child, false, horizon - 1);
    }

    /**
     * Génère l'arbre d'un nœud.
     *
     * @param parentNode  Le nœud auquel on crée son arbre.
     * @param isMaxPlayer Si les enfants du nœud que l'on crée sont {@code MAX}.
     * @param depth       La profondeur où l'on est.
     */
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
}

package fr.solo.awale;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Player {
    private String username;
    private int score;
    private Side side;
    protected Awale game;
    // Couleur du joueur dans le terminal (ne sert pas dans la logique du jeu)
    private Attribute color;

    /**
     * Constructeur avec le pseudo du joueur
     */
    public Player(String username) {
        this.username = username;
        score = 0;
        color = Attribute.WHITE_TEXT();
    }

    /**
     * Constructeur avec le pseudo du joueur + Sa couleur dans le terminal
     */
    public Player(String username, Attribute colorPlayer) {
        this(username);
        this.color = colorPlayer;
    }

    /**
     * Méthode permettant un joueur de rejoindre un jeu.
     *
     * @param game Le jeu à rejoindre.
     */
    public void joinGame(Awale game) {
        this.game = game;
        game.addPlayer(this);
    }

    /**
     * Joue le coup d'un joueur.
     *
     * @param holeSrc Le trou d'origine (du côté du joueur)
     * @return {@code true} = coup joué sans soucis ;<br/>
     * {@code false} = le joueur n'a pas pu jouer le coup
     * @see Awale#chooseHole(Player)
     */
    public boolean play(int holeSrc) {
        if (holeSrc < 0 || holeSrc > 5) {
            System.out.println(colorize("\tLe trou que vous avez choisi n'existe pas." +
                    "\n\tChoisissez-en un qui est entre 1 et 6.", Attribute.RED_TEXT()));
            return false;
        }

        Board board = game.getBoard();

        if (!board.isPlayable(holeSrc, side)) {
            System.out.println(colorize("\tVous ne pouvez pas jouer ce trou." +
                    "\n\tChoisissez-en un autre.", Attribute.RED_TEXT()));
            return false;
        }

        // On met à jour le plateau
        int lastHole = updateBoard(holeSrc, board);
        // Et on capture les graines
        captureSeed(board, lastHole);

        return true;
    }

    /**
     * Met à jour le plateau en distribuant les graines.
     *
     * @param holeSrc Le trou choisi par le joueur.
     * @param board   Le plateau sur lequel on distribue.
     * @return Le dernier trou de la distribution.<br/>
     * (-1 si pas de distribution chez l'ennemi, sinon {@code ∈ [0,5]})
     * @see Player#play(int)
     */
    private int updateBoard(int holeSrc, Board board) {
        Side sideEnemy = board.getOppositeSide(side);
        int inHand = board.clearHole(side, holeSrc);
        int lastHole = -1;

        while (inHand > 0) {
            // Distribution des graines en main sur notre plateau
            for (int i = holeSrc + 1; i <= 5 && inHand > 0; i++) {
                board.addSeed(side, i);
                inHand--;
            }

            // Puis, s'il en reste, on distribue sur l'autre partie du plateau
            for (int j = 0; j <= 5 && inHand > 0; j++) {
                board.addSeed(sideEnemy, j);
                inHand--;
                lastHole = j;
            }
        }
        return lastHole;
    }

    /**
     * Capture et met à jour le score du joueur.<br/>
     * Si {@code lastHole == -1}, alors on ne capture pas puisqu'on n'a pas distribué chez l'adversaire.
     *
     * @param board    Le plateau sur lequel on capture.
     * @param lastHole Le dernier trou de la distribution.
     * @see Player#play(int)
     */
    private void captureSeed(Board board, int lastHole) {
        if (lastHole != -1) {
            int seedsCollected = board.capturing(board.getOppositeSide(side), lastHole);
            score += seedsCollected;
            board.removeNbSeed(seedsCollected);
        }
    }

    // --- GETTERS/ SETTERS ---

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public Attribute getColor() {
        return color;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public void addPoints(int nb) {
        this.score += nb;
    }
}

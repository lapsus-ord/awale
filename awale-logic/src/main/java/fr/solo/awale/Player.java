package fr.solo.awale;

import com.diogonunes.jcolor.Attribute;

import java.util.Arrays;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Player {
	private String username;
	private int nbPoint;
	private Side side;
	private Awale game;
	// Couleur du joueur dans le terminal (ne sert pas dans la logique du jeu)
	private Attribute color;

	/**
	 * @param username pseudo du joueur.
	 */
	public Player(String username) {
		this.username = username;
		nbPoint = 0;
	}

	public Player(String username, Attribute colorPlayer) {
		this.username = username;
		this.color = colorPlayer;
		nbPoint = 0;
	}

	public String getUsername() {
		return username;
	}

	public int getNbPoint() {
		return nbPoint;
	}

	public Side getSide() {
		return side;
	}

	public Attribute getColor() {
		return color;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public void joinGame(Awale game) {
		this.game = game;
	}

	/**
	 * Joue le coup d'un joueur.
	 *
	 * @param trouSrc Le trou d'origine (du côté du joueur)
	 * @return {@code true} = coup joué sans soucis ;<br/>
	 * {@code false} = le joueur n'a pas pu jouer le coup
	 */
	public boolean play(int trouSrc) {
		if (trouSrc < 0 || trouSrc > 5) {
			System.out.println(colorize("\tLe trou que vous avez choisi n'existe pas." +
					"\n\tChoisissez-en un qui est entre 1 et 6.", Attribute.RED_TEXT()));
			return false;
		}

		Board board = game.getBoard();
		Side sideEnemy = board.getOppositeSide(side);

		if (!board.isPlayable(trouSrc, side)) {
			System.out.println(colorize("\tVous ne pouvez pas jouer ce trou." +
					"\n\tChoisissez-en un autre.", Attribute.RED_TEXT()));
			return false;
		}
		int inHand = board.clearTrou(side, trouSrc);
		int lastTrou = -1;

		while (inHand > 0) {
			// Distribution des graines en main sur notre plateau
			for (int i = trouSrc + 1; i <= 5 && inHand > 0; i++) {
				board.addGraine(side, i);
				inHand--;
			}

			// Puis, s'il en reste, on distribue sur l'autre partie du plateau
			for (int j = 0; j <= 5 && inHand > 0; j++) {
				board.addGraine(sideEnemy, j);
				inHand--;
				lastTrou = j;
			}
		}

		if (lastTrou != -1) {
			int ramassage = board.ramasser(sideEnemy, lastTrou);
			nbPoint += ramassage;
			board.removeNbGraine(ramassage);
		}

		return true;
	}

	public void addPoints(int nb){
		this.nbPoint += nb;
	}
}

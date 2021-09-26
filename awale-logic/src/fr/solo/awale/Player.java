package fr.solo.awale;

public class Player {
	private final String username;
	private final int nbPoint;
	private final Side side;

	/**
	 * @param username pseudo du joueur.
	 * @param side     côté joueur sur le plateau.<br/>
	 *                 false/true = haut/bas
	 */
	public Player(String username, Side side) {
		this.username = username;
		this.side = side;
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

	public boolean isOnTop() {
		return side.equals(Side.TOP);
	}

	public boolean isOnBottom() {
		return side.equals(Side.BOTTOM);
	}
}

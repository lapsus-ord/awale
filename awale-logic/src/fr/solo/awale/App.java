package fr.solo.awale;

public class App {
	public static void main(String[] args) {
		Board board = new Board();
		Player p1 = new Player("Bernard", Side.TOP);
		Player p2 = new Player("José", Side.BOTTOM);

		Awale jeu = new Awale(board, p1, p2);
		p1.setJeu(jeu);
		p2.setJeu(jeu);

		System.out.println(jeu);
		jeu.run();
	}
}

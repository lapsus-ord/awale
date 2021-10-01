package fr.solo.awale;

public class App {
	public static void main(String[] args) {
		Player p1 = new Player("Bernard");
		Player p2 = new Player("José");

		Awale jeu = new Awale(p1, p2);
		p1.joinGame(jeu);
		p2.joinGame(jeu);

		System.out.println(jeu);
		jeu.run();
	}
}

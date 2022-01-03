package fr.solo.awale.server.models;

import fr.solo.awale.logic.Awale;
import fr.solo.awale.logic.player.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WebPlayer {
	private String playerId;
	private Player player;
	private HashMap<String, Awale> games;

	public WebPlayer(String playerId, String username) {
		this.playerId = playerId;
		player = new Player(username);
		games = new HashMap<>();
	}

	public void addGame(String gameId, Awale newGame) {
		games.put(gameId, newGame);
	}

	public String getPlayerId() {
		return playerId;
	}

	public Player getPlayer() {
		return player;
	}

	public Set<String> getGamesId() {
		return games.keySet();
	}

	public HashMap<String, Awale> getGames() {
		return games;
	}
}

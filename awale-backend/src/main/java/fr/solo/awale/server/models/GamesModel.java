package fr.solo.awale.server.models;

import fr.solo.awale.logic.Awale;
import fr.solo.awale.logic.GameFactory;
import fr.solo.awale.logic.Side;
import fr.solo.awale.logic.player.Player;
import fr.solo.awale.logic.player.ai.AILevel;

import java.util.*;

import static fr.solo.awale.logic.Awale.Gamestate.WAITING_GAME;

public class GamesModel {
    private static GamesModel instance;
    private Map<String, Awale> games;
    private Map<String, WebPlayer> webPlayers;

    private GamesModel() {
        games = new HashMap<>();
        webPlayers = new HashMap<>();
    }

    public static GamesModel getInstance() {
        if (instance == null) {
            instance = new GamesModel();
        }
        return instance;
    }

    /**
     * On vérifie si on connaît déjà le joueur (si "non", on le sauvegarde)
     *
     * @return Le joueur qui se connecte
     */
    private WebPlayer connectPlayer(String userId, String username) {
        WebPlayer player = null;
        if (!webPlayers.containsKey(userId)) {
            player = new WebPlayer(userId, username);
            webPlayers.put(userId, player);
        } else {
            player = webPlayers.get(userId);
        }
        return player;
    }

    /**
     * Faire rejoindre notre joueur à une partie contre un joueur<br>
     * (En tant que joueur ou visiteur)
     */
    public void joinGame(String userId, String username, String gameId) {
        WebPlayer player = connectPlayer(userId, username);
        if (!games.containsKey(gameId)) { // Si la partie n'existe pas -> créer la partie
            Awale newGame = new GameFactory().createGame(player.getPlayer());
            games.put(gameId, newGame); // Ajout à la liste totale des jeux
            player.addGame(gameId, newGame); // Ajout à la liste des jeux du joueurs
        } else { // La partie existe déjà -> le joueur rejoint la partie
            player.getPlayer().joinGame(games.get(gameId));
        }
    }

    /**
     * Faire rejoindre notre joueur à une partie contre un bot<br>
     * (En tant que joueur ou visiteur)
     */
    public void createBotGame(String userId, String username, String gameId, AILevel level) {
        WebPlayer player = connectPlayer(userId, username);
        if (!games.containsKey(gameId)) { // Si la partie n'existe pas -> créer la partie
            try {
                Awale newGame = new GameFactory().createGameWithAI(player.getPlayer(), level);
                games.put(gameId, newGame); // Ajout à la liste totale des jeux
                player.addGame(gameId, newGame); // Ajout à la liste des jeux du joueurs
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else { // La partie existe déjà -> le joueur rejoint la partie
            player.getPlayer().joinGame(games.get(gameId));
        }
    }

    /**
     * Le joueur joue son coup (seulement s'il est dans la partie)
     * @return {@code true} s'il a pu jouer son coup
     */
    public boolean move(String userId, int hole, String gameId) {
        Awale game = games.get(gameId);
        Player player = webPlayers.get(userId).getPlayer();
        if (game.getPlayer(Side.TOP).equals(player) || game.getPlayer(Side.BOTTOM).equals(player)) {
            return game.playerPlayHisTurn(player, hole);
        }
        return false;
    }

    // --- GETTERS ---
    public String getJsonGames() {
        StringJoiner strJ = new StringJoiner(",", "[", "]");
        games.forEach((key, game) -> {
            strJ.add(game.toJson(key));
        });
        return strJ.toString();
    }

    public String getJsonWaitingGames() {
        StringJoiner strJ = new StringJoiner(",", "[", "]");
        games.forEach((key, game) -> {
            if (game.getState().equals(WAITING_GAME)) {
                strJ.add(game.toJson(key));
            }
        });
        return strJ.toString();
    }

    public String getJsonGame(String id) {
        return games.get(id).toJson(id);
    }

    public List<String> getWatchersFromGame(String gameId) {
        List<String> playersId = new ArrayList<>(2);
        webPlayers.values().forEach((webPlayer -> {
            if (webPlayer.getGamesId().contains(gameId)) {
                playersId.add(webPlayer.getPlayerId());
            }
        }));
        return playersId;
    }
}

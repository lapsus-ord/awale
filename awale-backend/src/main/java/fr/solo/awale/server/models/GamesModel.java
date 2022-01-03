package fr.solo.awale.server.models;

import fr.solo.awale.logic.Awale;
import fr.solo.awale.logic.GameFactory;
import fr.solo.awale.logic.Side;
import fr.solo.awale.logic.player.AbstractPlayer;
import fr.solo.awale.logic.player.Player;
import fr.solo.awale.logic.player.ai.AILevel;

import java.util.*;

import static fr.solo.awale.logic.Awale.Gamestate.END_GAME;
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
        WebPlayer webPlayer = connectPlayer(userId, username);
        if (!games.containsKey(gameId)) { // Si la partie n'existe pas -> créer la partie
            Awale newGame = new GameFactory().createGame(webPlayer.getPlayer());
            games.put(gameId, newGame);
            webPlayer.addGame(gameId, newGame);
        } else { // La partie existe déjà -> le joueur rejoint la partie
            webPlayer.getPlayer().joinGame(games.get(gameId));
            webPlayer.addGame(gameId, games.get(gameId));
        }
    }

    /**
     * Faire rejoindre notre joueur à une partie contre un bot<br>
     * (En tant que joueur ou visiteur)
     */
    public void createBotGame(String userId, String username, String gameId, AILevel level) {
        WebPlayer webPlayer = connectPlayer(userId, username);
        if (!games.containsKey(gameId)) { // Si la partie n'existe pas -> créer la partie
            try {
                Awale newGame = new GameFactory().createGameWithAI(webPlayer.getPlayer(), level);
                games.put(gameId, newGame);
                webPlayer.addGame(gameId, newGame);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else { // La partie existe déjà -> le joueur rejoint la partie
            webPlayer.getPlayer().joinGame(games.get(gameId));
            webPlayer.addGame(gameId, games.get(gameId));
        }
    }

    public void createPrefabGame(String gameId, int[][] board) {
        if (!games.containsKey(gameId)) { // Si la partie n'existe pas -> créer la partie
            try {
                Awale newGame = new GameFactory().createPrefabGame(board);
                games.put(gameId, newGame);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Le joueur joue son coup (seulement s'il est dans la partie)
     *
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

    public String getJsonGame(String gameId) {
        return games.get(gameId).toJson(gameId);
    }

    public List<String> getPlayersIdFromGame(String gameId) {
        List<String> playersId = new ArrayList<>(2);
        webPlayers.values().forEach((webPlayer -> {
            if (webPlayer.getGamesId().contains(gameId)) {
                playersId.add(webPlayer.getPlayerId());
            }
        }));
        return playersId;
    }

    public String getGamesFromPlayerId(String userId) {
        StringJoiner strJ = new StringJoiner(",", "[", "]");
        if (webPlayers.containsKey(userId)) {
            WebPlayer webPlayer = webPlayers.get(userId);
            webPlayer.getGames().forEach((key, game) -> {
                strJ.add(game.toJson(key));
            });
        }
        return strJ.toString();
    }

    public String getWinnerGame(String gameId) {
        if (games.get(gameId).getState().equals(END_GAME)) {
            return games.get(gameId).printWinnerToJson();
        }
        return null;
    }
}

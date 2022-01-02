package fr.solo.awale.server.controllers.commands;

import fr.solo.awale.logic.player.ai.AILevel;
import fr.solo.awale.server.controllers.GameController;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

public class JoinBot extends Command {

    public JoinBot(GameController controller) {
        super(controller);
    }

    @Override
    public boolean execute(String payload, WebSocketSession session) {
        // Traitement JSON
        Map<String, Object> jsonMap = getJsonMap(payload);
        if (isJsonNotValid(jsonMap, "userId", "username", "gameId", "level"))
            return false;
        String userId = jsonMap.get("userId").toString();
        String username = jsonMap.get("username").toString();
        String gameId = jsonMap.get("gameId").toString();
        AILevel level = AILevel.fromString(jsonMap.get("level").toString());
        // Exécution de la bonne commande
        controller.getSessions().put(userId, session);
        gameService.createBotGame(userId, username, gameId, level);
        // Envoi du résultat au joueur
        sendToPlayer(userId, "update," + gameService.getJsonGame(gameId));
        return true;
    }

}

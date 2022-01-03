package fr.solo.awale.server.controllers.commands;

import fr.solo.awale.server.controllers.GameController;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

public class Join extends Command {

    public Join(GameController controller) {
        super(controller);
    }

    @Override
    public boolean execute(String payload, WebSocketSession session) {
        // Traitement JSON
        Map<String, Object> jsonMap = getJsonMap(payload);
        if (isJsonNotValid(jsonMap, "userId", "username", "gameId"))
            return false;
        String userId = jsonMap.get("userId").toString();
        String username = jsonMap.get("username").toString();
        String gameId = jsonMap.get("gameId").toString();
        controller.getSessions().put(userId, session);
        // On ajoute le joueur a une partie (crée ou non)
        gameService.joinGame(userId, username, gameId);
        // Envoi du résultat aux joueurs
        controller.sendToGame(gameId, "update," + gameService.getJsonGame(gameId));
        return true;
    }

}

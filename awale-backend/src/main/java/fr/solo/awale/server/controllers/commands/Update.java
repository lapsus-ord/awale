package fr.solo.awale.server.controllers.commands;

import fr.solo.awale.server.controllers.GameController;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

public class Update extends Command {

    public Update(GameController controller) {
        super(controller);
    }

    @Override
    public boolean execute(String payload, WebSocketSession session) {
        // Traitement JSON
        Map<String, Object> jsonMap = getJsonMap(payload);
        if (isJsonNotValid(jsonMap, "gameId"))
            return false;
        String gameId = jsonMap.get("gameId").toString();
        String gameState = gameService.getJsonGame(gameId);
        if (gameState != null) {
            // Envoi du résultat aux joueurs
            controller.sendToGame(gameId, "update," + gameState);
            return true;
        }
        return false;
    }

}

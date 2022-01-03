package fr.solo.awale.server.controllers.commands;

import fr.solo.awale.server.controllers.GameController;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

public class Move extends Command {

    public Move(GameController controller) {
        super(controller);
    }

    @Override
    public boolean execute(String payload, WebSocketSession session) {
        // Traitement JSON
        Map<String, Object> jsonMap = getJsonMap(payload);
        if (isJsonNotValid(jsonMap, "userId", "hole", "gameId"))
            return false;
        String userId = jsonMap.get("userId").toString();
        int hole = (int) jsonMap.get("hole");
        String gameId = jsonMap.get("gameId").toString();
        // Exécution de la bonne commande
        if (gameService.move(userId, hole, gameId)) {
            // Envoi du résultat aux joueurs
            controller.sendToGame(gameId, "update," + gameService.getJsonGame(gameId));
            return true;
        } else {
            controller.sendToPlayer(userId, "error,Vous ne pouvez pas jouer ce coup...");
            return false;
        }
    }

}

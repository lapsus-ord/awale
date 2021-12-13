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
        Map<String, Object> jsonMap = jsonParser.parseMap(payload);
        if (!(jsonMap.containsKey("userId") && jsonMap.containsKey("hole") && jsonMap.containsKey("gameId")))
            return false;
        String userId = jsonMap.get("userId").toString();
        int hole = (int) jsonParser.parseMap(payload).get("hole");
        String gameId = jsonMap.get("gameId").toString();
        if (!gameService.isPlayerInTheGame(userId, gameId))
            return false;
        // Exécution de la bonne commande
        boolean state = gameService.move(userId, hole, gameId);
        // Envoi du résultat au joueur
        sendToPlayer(userId, gameService.getJsonGame(gameId));
        return state;
    }

}

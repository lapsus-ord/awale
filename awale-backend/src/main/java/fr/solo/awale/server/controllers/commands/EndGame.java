package fr.solo.awale.server.controllers.commands;

import fr.solo.awale.server.controllers.GameController;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

public class EndGame extends Command {

    public EndGame(GameController controller) {
        super(controller);
    }

    @Override
    public boolean execute(String payload, WebSocketSession session) {
        // Traitement JSON
        Map<String, Object> jsonMap = getJsonMap(payload);
        if (isJsonNotValid(jsonMap, "gameId"))
            return false;
        String gameId = jsonMap.get("gameId").toString();
        String result = gameService.getWinnerGame(gameId);
        if (result != null) {
            controller.sendToGame(gameId, "winConfirmed," + gameService.getWinnerGame(gameId));
            return true;
        }
        return false;
    }

}

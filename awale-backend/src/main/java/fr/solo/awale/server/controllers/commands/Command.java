package fr.solo.awale.server.controllers.commands;

import fr.solo.awale.server.controllers.GameController;
import org.springframework.boot.json.JsonParser;
import fr.solo.awale.server.services.GameService;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.socket.WebSocketSession;

public abstract class Command {
    protected GameService gameService;
    protected JsonParser jsonParser;
    protected GameController controller;

    public Command(GameController controller) {
        this.gameService = GameService.getInstance();
        jsonParser = JsonParserFactory.getJsonParser();
        this.controller = controller;
    }

    public abstract boolean execute(String payload, WebSocketSession session);

    protected void sendToPlayer(String userId, String msg) {
        controller.sendtoPlayer(userId, msg);
    }
}

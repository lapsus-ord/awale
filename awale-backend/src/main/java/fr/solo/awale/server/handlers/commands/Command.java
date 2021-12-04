package fr.solo.awale.server.handlers.commands;

import org.springframework.boot.json.JsonParser;
import fr.solo.awale.server.services.GameService;
import org.springframework.boot.json.JsonParserFactory;

public abstract class Command {
    protected GameService gameService;
    protected JsonParser jsonParser;

    public Command() {
        this.gameService = GameService.getInstance();
        jsonParser = JsonParserFactory.getJsonParser();
    }

    public abstract boolean execute(String payload);
}

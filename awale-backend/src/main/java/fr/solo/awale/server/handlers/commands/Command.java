package fr.solo.awale.server.handlers.commands;

import org.springframework.boot.json.JsonParser;
import fr.solo.awale.server.services.GameService;
import org.springframework.boot.json.JsonParserFactory;

public abstract class Command {
    protected GameService gameService;
    protected JsonParser jsonParser;
    private CommandHistory history;

    public Command() {
        this.gameService = GameService.getInstance();
        jsonParser = JsonParserFactory.getJsonParser();
        history = new CommandHistory();
    }

    public abstract boolean execute(String payload);
}

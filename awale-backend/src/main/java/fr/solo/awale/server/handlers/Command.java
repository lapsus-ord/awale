package fr.solo.awale.server.handlers;

import com.fasterxml.jackson.core.JsonParser;
import fr.solo.awale.server.services.GameService;

public abstract class Command {

    private CommandHistory history;

    public Command(CommandHistory history) {
        history = new CommandHistory();
    }

    abstract void execute(GameService game, JsonParser jsonParser, String payload);


}

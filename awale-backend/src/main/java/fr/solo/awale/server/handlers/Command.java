package fr.solo.awale.server.handlers;

public abstract class Command {

    private CommandHistory history;

    public Command(CommandHistory history){
        history = new CommandHistory();
    }
     void execute(GameService game, JsonParser jsonParser, String payload);


}

package fr.solo.awale.server.handlers.commands;

import fr.solo.awale.server.handlers.Command;

public class Move implements Command {
    private String user_id;
    private int hole;
    @Override
    public void execute(GameService, game, JsonParser, json, String msg) {
        user_id = (String) jsonParser.parseMap(command[1]).get("userId");
        hole = (int) jsonParser.parseMap(command[1]).get("hole");
        game.move(user_id, hole);
    }
}

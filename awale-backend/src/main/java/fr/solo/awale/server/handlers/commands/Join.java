package fr.solo.awale.server.handlers.commands;

import com.fasterxml.jackson.core.JsonParser;
import fr.solo.awale.server.handlers.Command;
import fr.solo.awale.server.services.GameService;

public class Join implements Command {

    private String user_id;
    private String username;

    public Join(String ui, String u){
        this.user_id = ui;
        this.username = u;
    }

    @Override
    public void execute(GameService game, JsonParser jsonParser, String payload) {
        user_id = (String) jsonParser.parseMap(command[1]).get("userId");
        username = (String) jsonParser.parseMap(command[1]).get("username");
        game.addPlayer(user_id, username);
    }
}

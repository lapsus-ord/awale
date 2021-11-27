package fr.solo.awale.server.handlers.commands;

import java.util.Map;

public class Join extends Command {

    @Override
    public boolean execute(String payload) {
        Map<String, Object> jsonMap = jsonParser.parseMap(payload);
        if (!(jsonMap.containsKey("userId") && jsonMap.containsKey("username")))
            return false;
        String userId = jsonMap.get("userId").toString();
        String username = jsonMap.get("username").toString();
        return gameService.addPlayer(userId, username);
    }

}

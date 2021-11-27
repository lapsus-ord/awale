package fr.solo.awale.server.handlers.commands;

import java.util.Map;

public class Move extends Command {

    @Override
    public boolean execute(String payload) {
        Map<String, Object> jsonMap = jsonParser.parseMap(payload);
        if (!(jsonMap.containsKey("userId") && jsonMap.containsKey("hole")))
            return false;
        String userId = jsonMap.get("userId").toString();
        int hole = (int) jsonParser.parseMap(payload).get("hole");
        return gameService.move(userId, hole);
    }

}

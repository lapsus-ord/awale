package fr.solo.awale.server.handlers.commands;

import fr.solo.awale.server.handlers.Command;
import fr.solo.awale.server.handlers.CommandHistory;

public class Move extends Command {
    private String user_id;
    private int hole;

    public Move(CommandHistory history) {
        super(history);
    }
}

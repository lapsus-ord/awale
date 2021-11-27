package fr.solo.awale.server.handlers.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private Map<String, Command> commands;
    private CommandHistory history;

    public CommandManager() {
        commands = new HashMap<>();
        history = new CommandHistory();
    }

    public void setCommand(String name, Command command) {
        commands.put(name, command);
    }

    public void executeCommand(String command, String payload) {
        if (commands.containsKey(command)) {
            if (!commands.get(command).execute(payload))
                System.out.println("Erreur dans la commande " + command);
            else
                history.push(command);
        } else {
            System.out.println("La commande n'existe pas.");
        }
    }

    public String getHistory() {
        return history.toString();
    }
}

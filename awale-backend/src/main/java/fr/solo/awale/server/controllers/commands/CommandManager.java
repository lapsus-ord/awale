package fr.solo.awale.server.controllers.commands;

import org.springframework.web.socket.WebSocketSession;

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

    public void executeCommand(String command, String payload, WebSocketSession session) {
        if (commands.containsKey(command)) {
            if (!commands.get(command).execute(payload, session))
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

package fr.solo.awale.server.handlers;

import fr.solo.awale.server.handlers.commands.CommandManager;
import fr.solo.awale.server.handlers.commands.Join;
import fr.solo.awale.server.handlers.commands.Move;
import fr.solo.awale.server.services.GameService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GameHandler extends TextWebSocketHandler {
    private List<WebSocketSession> sessions;
    private GameService service;
    private CommandManager manager;

    public GameHandler() {
        sessions = new ArrayList<>();
        service = GameService.getInstance();
        manager = new CommandManager();
        initCommands();
    }

    private void initCommands() {
        manager.setCommand("join", new Join());
        manager.setCommand("move", new Move());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage msg) {
        String[] command = msg.getPayload().split(",", 2);
        // System.out.println(command[0] + ": " + command[1]);
        manager.executeCommand(command[0], command[1]);
        System.out.println(manager.getHistory());
        sendToAll(service.toString());
    }

    private void sendToAll(String msg) {
        sessions.forEach(el -> {
            try {
                el.sendMessage(new TextMessage(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        for (WebSocketSession player : sessions) {
            System.out.println("id: " + player.getId() + "--- uri: " + player.getUri());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }
}

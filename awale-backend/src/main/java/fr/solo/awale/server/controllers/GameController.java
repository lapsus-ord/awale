package fr.solo.awale.server.controllers;

import com.diogonunes.jcolor.Attribute;
import fr.solo.awale.server.controllers.commands.*;
import fr.solo.awale.server.models.GamesModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.diogonunes.jcolor.Ansi.colorize;

@CrossOrigin
@RestController
@Component
public class GameController extends TextWebSocketHandler {
    private Map<String, WebSocketSession> sessions;
    private GamesModel service;
    private CommandManager manager;

    public GameController() {
        sessions = new HashMap<>();
        service = GamesModel.getInstance();
        manager = new CommandManager();
        initCommands();
    }

    private void initCommands() {
        manager.setCommand("join", new Join(this));
        manager.setCommand("join-bot", new JoinBot(this));
        manager.setCommand("move", new Move(this));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage msg) {
        String[] command = msg.getPayload().split(",", 2);
        System.out.println(colorize(msg.getPayload(), Attribute.RED_TEXT()));
        manager.executeCommand(command[0], command[1], session);
        System.out.println(manager.getHistory());
    }

    public void sendtoPlayer(String userId, String msg) {
        try {
            sessions.get(userId).sendMessage(new TextMessage(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendToAll(String msg) {
        sessions.forEach((k, el) -> {
            try {
                el.sendMessage(new TextMessage(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public Map<String, WebSocketSession> getSessions() {
        return sessions;
    }

    @GetMapping("/waiting-games")
    public String getAwaitingGames() {
        service.joinGame("1", "John", "1");
        service.joinGame("2", "Bernard", "3");
        return service.getJsonWaitingGames();
    }
}

package fr.solo.awale.server.handlers;

import fr.solo.awale.server.services.GameService;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class GameHandler extends TextWebSocketHandler {
    private List<WebSocketSession> players;
    private GameService game;
    private JsonParser jsonParser;
    private Command command;

    public GameHandler() {
        players = new ArrayList<>();
        game = GameService.getInstance();
        jsonParser = JsonParserFactory.getJsonParser();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage msg) {
        String[] command = msg.getPayload().split(",", 2);
        // System.out.println(command[0] + ": " + command[1]);
        command.execute(game, jsonParser, payload[1]);
        sendToAll(game.toString());
    }

    private void sendToAll(String msg) {
        players.forEach(el -> {
            try {
                el.sendMessage(new TextMessage(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        players.add(session);
        for (WebSocketSession player : players) {
            System.out.println("id: " + player.getId() + "--- uri: " + player.getUri());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        players.remove(session);
    }
}

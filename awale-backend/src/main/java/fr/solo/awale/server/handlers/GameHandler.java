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

    public GameHandler() {
        players = new ArrayList<>();
        game = GameService.getInstance();
        jsonParser = JsonParserFactory.getJsonParser();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage msg) {
        String[] command = msg.getPayload().split(",", 2);
        // System.out.println(command[0] + ": " + command[1]);
        String userId;

        switch (command[0]) {
            case "join":
                userId = (String) jsonParser.parseMap(command[1]).get("userId");
                String username = (String) jsonParser.parseMap(command[1]).get("username");
                game.addPlayer(userId, username);
                sendToAll(game.toString());
                break;
            case "move":
                userId = (String) jsonParser.parseMap(command[1]).get("userId");
                int hole = (int) jsonParser.parseMap(command[1]).get("hole");
                game.move(userId, hole);
                sendToAll(game.toString());
                break;
            default:
                System.out.println("Commande inconnue !");
        }
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

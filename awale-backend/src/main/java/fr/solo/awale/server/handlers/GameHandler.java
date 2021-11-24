package fr.solo.awale.server.handlers;

import fr.solo.awale.server.services.GameService;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class GameHandler extends TextWebSocketHandler {
    private List<WebSocketSession> players = new ArrayList<>();
    private GameService game = GameService.getInstance();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage msg) {
        String[] command = msg.getPayload().split(",", 2);
        System.out.println(command[0] + ": " + command[1]);

        switch (command[0]) {
            case "join":
                System.out.println("Un joueur rejoint la partie");
                game.addPlayer("anonymous");
                sendToAll(game.toString());
                break;
            case "move":
                System.out.println("Un joueur joue un coup");
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

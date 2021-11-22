package fr.solo.awale.server.handlers;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class GameHandler extends TextWebSocketHandler {
    List<WebSocketSession> players = new CopyOnWriteArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage msg) {
        System.out.println("Nouveau message : " + msg.getPayload());
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

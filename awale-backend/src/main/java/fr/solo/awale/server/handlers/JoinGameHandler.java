package fr.solo.awale.server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.solo.awale.server.dtos.SimpleMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class JoinGameHandler extends TextWebSocketHandler {
    List<WebSocketSession> players = new CopyOnWriteArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage msg) throws JsonProcessingException {
        System.out.println("Nouveau message : " + new ObjectMapper().readValue(msg.getPayload(), SimpleMessage.class));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        players.add(session);
        for (WebSocketSession player : players) {
            System.out.println("id: " + player.getId() + "\nuri: " + player.getUri());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        players.remove(session);
    }
}

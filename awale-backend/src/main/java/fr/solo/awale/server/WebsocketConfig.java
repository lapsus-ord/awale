package fr.solo.awale.server;

import fr.solo.awale.server.handlers.JoinGameHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new JoinGameHandler(), "/join")
                .setAllowedOrigins("*");
        registry.addHandler(new JoinGameHandler(), "/play")
                .setAllowedOrigins("*");
    }

}

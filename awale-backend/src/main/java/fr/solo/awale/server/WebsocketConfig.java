package fr.solo.awale.server;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {
    //Si besoin dans le futur
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Broker pour renvoyer des messages sur les destinations qui commencent par ce préfixe :
        //config.enableSimpleBroker("/prefix-vers-le-client");

        // Le préfixe des endpoints @MessageMapping (donc vers nous) :
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp")
                // Allow the origin http://localhost:{port} to send messages to us. (Base URL of the client)
                .setAllowedOriginPatterns("https://webinfo.iutmontp.univ-montp2.fr:[*]");
    }

}

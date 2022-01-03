package fr.solo.awale.server;

import fr.solo.awale.logic.player.ai.AILevel;
import fr.solo.awale.server.models.GamesModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class AppServer {

    public static void main(String[] args) {
        SpringApplication.run(AppServer.class, args);
        int[][] cellExAequo = {{1, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0}};
        int[][] cellPlayer2Win = {{0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 6, 0}};
        GamesModel.getInstance().createPrefabGame(UUID.randomUUID().toString(), cellExAequo);
        GamesModel.getInstance().createPrefabGame(UUID.randomUUID().toString(), cellPlayer2Win);
    }

}

package org.example.manager;

import org.example.game.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameManager {
    private static Map<String, Game> activeGames = new HashMap<>();

    public String createNewGame() {
        String gameId = UUID.randomUUID().toString();
        Game game = new Game();
        game.generateBoard("Full");
        activeGames.put(gameId, game);
        return gameId;
    }

    public Game getGame(String gameId) {
        return activeGames.get(gameId);
    }
}

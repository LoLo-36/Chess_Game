package org.example.manager;

import org.example.game.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameManager {
    private static Map<String, Game> activeGames = new HashMap<>();

    public String createGame(String type, String hostPlayerId) {
        String gameId = UUID.randomUUID().toString();
        Game game = new Game();
        game.generateBoard(type);

        game.getPlayer1().setId(hostPlayerId);

        activeGames.put(gameId, game);
        return gameId;
    }

    public boolean joinGame(String gameId, String joiningPlayerId) {
        Game game = activeGames.get(gameId);
        if (game == null) return false;

        return game.joinGame(joiningPlayerId);
    }

    public Game getGame(String gameId) {
        return activeGames.get(gameId);
    }
}

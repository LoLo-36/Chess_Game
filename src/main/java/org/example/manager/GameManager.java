package org.example.manager;

import org.example.game.Game;
import org.example.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameManager {
    private static Map<String, Game> activeGames = new HashMap<>();

    public String createGame(String type, User hostPlayer) {
        String gameId = UUID.randomUUID().toString();
        Game game = new Game();
        game.generateBoard(type);

        game.joinGame(hostPlayer);

        activeGames.put(gameId, game);
        return gameId;
    }

    public boolean joinGame(String gameId, User joiningPlayer) {
        Game game = activeGames.get(gameId);
        if (game == null) return false;

        return game.joinGame(joiningPlayer);
    }

    public Game getGame(String gameId) {
        return activeGames.get(gameId);
    }
}

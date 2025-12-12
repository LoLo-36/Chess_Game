package org.example.manager;

import org.example.game.Game;
import org.example.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manages the lifecycle of active games, including creation, joining,
 * and retrieval of game instances.
 * <p>
 * This class stores active games in memory using a {@link HashMap}, where
 * each game is identified by a unique game ID generated via {@link UUID}.
 * </p>
 */
public class GameManager {
    private static Map<String, Game> activeGames = new HashMap<>();

    /**
     * Creates a new game of the specified type and assigns the host player to it.
     * <p>
     * A unique game ID is generated for each game. The board is initialized
     * based on the provided game type, and the host player automatically joins.
     * </p>
     *
     * @param type       the type of the game (e.g., "chess", "checkers")
     * @param hostPlayer the player creating and hosting the game
     * @return the unique ID of the newly created game
     */
    public String createGame(String type, User hostPlayer) {
        String gameId = UUID.randomUUID().toString();
        Game game = new Game();
        game.generateBoard(type);

        game.joinGame(hostPlayer);

        activeGames.put(gameId, game);
        return gameId;
    }

    /**
     * Allows a player to join an existing game.
     *
     * @param gameId        the ID of the game to join
     * @param joiningPlayer the player attempting to join the game
     * @return {@code true} if the join operation succeeded,
     *         {@code false} if the game does not exist or the join fails
     */
    public boolean joinGame(String gameId, User joiningPlayer) {
        Game game = activeGames.get(gameId);
        if (game == null) return false;

        return game.joinGame(joiningPlayer);
    }

    public Game getGame(String gameId) {
        return activeGames.get(gameId);
    }
}

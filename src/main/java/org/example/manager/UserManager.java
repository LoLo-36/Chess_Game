package org.example.manager;

import org.example.user.User;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages user registration and online user tracking.
 * <p>
 * This class maintains a thread-safe collection of online users using
 * {@link ConcurrentHashMap}, and provides methods for registering users,
 * retrieving users by ID, and removing users from the online list.
 * </p>
 */
public class UserManager {
    private static ConcurrentHashMap<String, User> onlineUsers = new ConcurrentHashMap<>();

    /**
     * Registers a new user with the given username.
     * <p>
     * A unique user ID is automatically generated using {@link UUID}.
     * The created user is added to the list of online users.
     * </p>
     *
     * @param username the username of the new user
     * @return the newly created {@link User} instance
     */
    public User registerUser(String username) {
        String id = UUID.randomUUID().toString();
        User user = new User(id, username);
        onlineUsers.put(id, user);
        return user;
    }

    public User getUserById(String id) {
        return onlineUsers.get(id);
    }

    public void removeUser(String id) {
        onlineUsers.remove(id);
    }
}

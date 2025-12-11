package org.example.manager;

import org.example.entities.Player;
import org.example.user.User;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
    private static ConcurrentHashMap<String, User> onlineUsers = new ConcurrentHashMap<>();

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

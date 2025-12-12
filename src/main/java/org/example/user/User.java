package org.example.user;

/**
 * Represents a user in the system with a unique identifier and a username.
 * This class provides basic information about a user and allows modifying
 * the user's ID and username after creation.
 */
public class User {
    private String id;
    private String username;

    /**
     * Creates a new User with the given id and username.
     *
     * @param id        the unique identifier of the user
     * @param username  the username of the user
     */
    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

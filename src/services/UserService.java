package services;

import model.User;

public class UserService {
    private final User user;

    public UserService(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

import controller.UserController;
import services.UserService;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserService();
        UserController userController = new UserController(userService);

        userController.startUserRegister();
    }
}
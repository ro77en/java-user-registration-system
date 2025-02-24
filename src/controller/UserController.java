package controller;

import model.User;
import services.UserService;

import java.io.*;
import java.util.*;

public class UserController {

    private final List<String> questions = new ArrayList<>();
    private final List<String> userInputs = new ArrayList<>();

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void startUserRegister() {
        readQuestionsFile();

        if (questions.isEmpty()) {
            System.out.println("No questions added to forms");
            return;
        }

        getUserInputs();

        if (validateUserInputs(userInputs)) {
            User user = userService.createUser(userInputs);
            userService.saveUser(user);
        }

    }

    public void readQuestionsFile() {
        File file = new File("src/resources/form.txt");

        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                questions.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s", e.getMessage());
        } catch (IOException e) {
            System.out.printf("Unexpected error: %s", e.getMessage());
            e.printStackTrace();
        }
    }

    private void getUserInputs() {
        try (Scanner sc = new Scanner(System.in).useLocale(Locale.US)) {
            for (String question : questions) {
                System.out.println(question);
                userInputs.add(sc.nextLine());
            }
        } catch (NoSuchElementException e) {
            System.out.printf("Error: No input found: %s", e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean validateUserInputs(List<String> userInputs) {
        if (userInputs.size() < 4) {
            System.out.println("Please, submit all answers");
            return false;
        }

        try {
            Integer.parseInt(userInputs.get(2));
            Float.parseFloat(userInputs.get(3));
        } catch (NumberFormatException e) {
            System.out.printf("Error: invalid age or height data format: %s", e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

package services;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static final Integer MIN_AGE = 1;
    private static final Integer MAX_AGE = 120;
    private static final Float MIN_HEIGHT = 0.0f;
    private static final Float MAX_HEIGHT = 2.5f;

    private List<User> usersList = new ArrayList<>();

    public User createUser(List<String> userInputs) {

        String name = userInputs.get(0);
        String email = userInputs.get(1);
        int age = Integer.parseInt(userInputs.get(2));
        float height = Float.parseFloat(userInputs.get(3));

        if (age < MIN_AGE || age > MAX_AGE) {
            System.out.println("Invalid inputs. Age must be between 0 and 120.");
            return null;
        }

        if (height < MIN_HEIGHT || height > MAX_HEIGHT) {
            System.out.println("Invalid inputs. Height must be between 0.0 and 2.5 (meters)");
            return null;
        }

        User user = new User(name, email, age, height);
        usersList.add(user);
        return user;
    }


    public void saveUser(User user) {
        String userName = user.getName().toUpperCase().replaceAll("\\s+", "");

        File file = new File("src/resources/users/" + usersList.size() + "-" + userName + ".txt");

        try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(user.getName() + "\n");
            bw.write(user.getEmail() + "\n");
            bw.write(String.valueOf(user.getAge()) + "\n");
            bw.write(String.valueOf(user.getHeight()) + "\n");
            System.out.printf("User saved successfully!%n %s", user);

        } catch (FileNotFoundException e) {
            System.out.println("Users file not found");
        } catch (IOException e) {
            System.out.printf("Error: %s", e.getMessage());
            e.printStackTrace();
        }
    }
}

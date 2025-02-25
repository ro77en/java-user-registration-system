package services;

import model.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserService {

    private static final Integer MIN_AGE = 1;
    private static final Integer MAX_AGE = 120;
    private static final Float MIN_HEIGHT = 0.0f;
    private static final Float MAX_HEIGHT = 2.5f;

    private List<User> usersList = new ArrayList<>();

    public User createUser(List<String> userInputs) throws IllegalArgumentException {

        String name = userInputs.get(0);
        String email = userInputs.get(1);
        int age = isAgeValid(userInputs.get(2));
        float height = isHeightValid(userInputs.get(3));

        User user = new User(name, email, age, height);
        usersList.add(user);
        return user;
    }

    public Integer getFilesAmount() throws IOException {
        try (Stream<Path> files = Files.list(Paths.get("src/resources/users/"))) {
            int filesCount = (int) files.count();

            return filesCount + 1;

        }
    }


    public void saveUser(User user) throws Exception {
        String userName = user.getUppercasedName().replaceAll("\\s+", "");

        File file = new File("src/resources/users/" + getFilesAmount() + "-" + userName + ".txt");

        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);) {
            System.out.println(usersList.size());

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

    public Integer isAgeValid(String ageStr) {
        int age = Integer.parseInt(ageStr);
        if (age < MIN_AGE || age > MAX_AGE) {
            throw new IllegalArgumentException("Invalid inputs. Age must be between " + MIN_AGE + " and " + MAX_AGE);
        }
        return age;
    }

    public Float isHeightValid(String heightStr) {
        float height = Float.parseFloat(heightStr);
        if (height < MIN_HEIGHT || height > MAX_HEIGHT) {
            throw new IllegalArgumentException("Invalid inputs. Height must be between 0.0 and 2.5 (meters)");
        }
        return height;
    }

}

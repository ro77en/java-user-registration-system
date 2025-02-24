package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UserController {

    public UserController() {
    }

    public void startUserRegister   () {
        File file = new File ("src/resources/form.txt");
        Scanner sc = new Scanner(System.in).useLocale(Locale.US);
        try(FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {

            String userName;
            String userEmail;
            String userAge;
            String userHeight;

            List<String> questions = new ArrayList<>();

            String line;

            while ((line = br.readLine()) != null) {
                questions.add(line);
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s", e.getMessage());
        } catch (IOException e) {
            System.out.printf("Unexpected error: %s", e.getMessage());
            e.printStackTrace();
        }

    }
}

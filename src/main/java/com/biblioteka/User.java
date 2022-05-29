package com.biblioteka;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringJoiner;

public class User {
    private final String name;
    private final String surname;
    private final String email;
    private static int clientCounter;
    private final String clientId;
    private static final String USER_FILE_PATH = "src/main/resources/users.txt";

    public User(String name, String surname, String email) {
        clientCounter = (int) countUsers(USER_FILE_PATH);
        this.name = name;
        this.surname = surname;
        this.email = email;
        clientId =  String.format("%08d" , ++clientCounter);
    }

    public static void generateUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj imie: ");
        String name = sc.next();
        System.out.println("Podaj nazwisko: ");
        String surname = sc.next();
        System.out.println("Podaj email: ");
        String email = sc.next();
        User user = new User(name, surname, email);
        user.addUserToFile();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if (!Objects.equals(name, user.name)) return false;
        if (!Objects.equals(surname, user.surname)) return false;
        if (!Objects.equals(email, user.email)) return false;
        return Objects.equals(clientId, user.clientId);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .add("email='" + email + "'")
                .add("clientId='" + clientId + "'")
                .toString();
    }

    private void addUserToFile() {
        try {
            FileWriter fw = new FileWriter(USER_FILE_PATH, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.toString());
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long countUsers(String fileName) {
        Path path = Paths.get(fileName);
        long lines = 0;
        try {
            lines = Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;

    }
}

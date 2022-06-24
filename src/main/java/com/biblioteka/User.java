package com.biblioteka;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class User {
    private static final String USER_FILE_PATH = "src/main/resources/users.txt";
    private static int clientCounter = (int) countUsers(USER_FILE_PATH);
    private final String name;
    private final String surname;
    private final String email;
    private final String clientId;
    private final List<String> taken;

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.taken = new LinkedList<>();
        clientId = String.format("%08d", ++clientCounter);
    }

    public void addBook(String isbn) {
        taken.add(isbn);
    }

    public static void generateUser() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj imie: ");
        String name = sc.next();
        System.out.println("Podaj nazwisko: ");
        String surname = sc.next();
        System.out.println("Podaj email: ");
        String email = sc.next();
        User user = new User(name, surname, email);
        if (user.isTaken()) {
            System.out.println("Operacja nieudana - użytkownik o takim emailu już istnieje");
        } else {
            FileHelper.addObjectToFile(USER_FILE_PATH, user.toString());
        }
    }

    private static long countUsers(String fileName) {
        Path path = Paths.get(fileName);
        long lines = 0;
        try {
            lines = Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;

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
                .add("taken=" + taken)
                .toString();
    }

    private boolean isTaken() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(USER_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                final String lineFromFile = scanner.nextLine().toLowerCase();
                if (lineFromFile.contains("email='" + this.email + "'")) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getEmail() {
        return email;
    }
}

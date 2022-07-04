package com.biblioteka;

import java.util.*;

public class User {
    private static int clientCounter = 0;
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

    public static User generateUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj imie: ");
        String name = sc.next();
        System.out.println("Podaj nazwisko: ");
        String surname = sc.next();
        System.out.println("Podaj email: ");
        String email = sc.next().toLowerCase();
        return new User(name, surname, email);
    }

    public static User getUserByEmail(String email, Set<User> users) throws UserNotFount {
        for (User u : users) {
            if (u.getEmail().toLowerCase().contains(email.toLowerCase())) {
                return u;
            }
        }
        throw new UserNotFount("There is no user with email: " + email);
    }

    public void addBook(String isbn) {
        taken.add(isbn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
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

    public boolean hasBook(String isbn) {
        for (String t : taken) {
            if (t.contains(isbn)) {
                return true;
            }
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getTaken() {
        return taken;
    }
}

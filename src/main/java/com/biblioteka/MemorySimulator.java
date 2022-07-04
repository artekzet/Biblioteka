package com.biblioteka;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MemorySimulator {
    private static MemorySimulator instance = null;
    public Set<User> users;
    public List<Book> books;

    private MemorySimulator() {
        users = new LinkedHashSet<>();
        books = new LinkedList<>();
    }

    public static MemorySimulator getInstance() {
        if (instance == null) {
            instance = new MemorySimulator();
        }
        return instance;
    }

    public boolean userExists(String email) {
        for (User u : users) {
            if (u.getEmail().toLowerCase().contains(email.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void getAllUsers() {
        users.forEach(System.out::println);
    }

    public void getAllBooks() {
        books.forEach(System.out::println);
    }
}

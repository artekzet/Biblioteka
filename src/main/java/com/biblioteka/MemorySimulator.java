package com.biblioteka;

import java.util.LinkedList;
import java.util.List;

public class MemorySimulator {
    private static MemorySimulator instance = null;
    public List<User> users;
    public List<Book> books;

    private MemorySimulator() {
        users = new LinkedList<>();
        books = new LinkedList<>();
    }

    public static MemorySimulator getInstance() {
        if (instance == null) {
            instance = new MemorySimulator();
        }
        return instance;
    }
}

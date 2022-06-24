package com.biblioteka;

import java.util.*;

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
}

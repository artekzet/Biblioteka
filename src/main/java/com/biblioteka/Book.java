package com.biblioteka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Book {
    private static final String BOOKS_TXT = "src/main/resources/books.txt";
    public String title;
    public String author;
    public String isbn;
    public int yearOfRelease;
    public boolean isAvailable;
    public int count;
    public int free;

    public Book(String title, String author, String isbn, int yearOfRelease, boolean isAvailable, int count) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.yearOfRelease = yearOfRelease;
        this.isAvailable = isAvailable;
        this.count = count;
        this.free = count;
    }

    public static Book generateBook() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj tytul: ");
        String title = sc.next();
        System.out.println("Podaj nazwisko autora: ");
        String surname = sc.next();
        System.out.println("Podaj isbn: ");
        String isbn = sc.next();
        System.out.println("Podaj rok wydania: ");
        int year = sc.nextInt();
        System.out.println("Ile sztuk tej książki chcesz dodac?");
        int count = sc.nextInt();
        Book book = new Book(title, surname, isbn, year, true, count);

        if (isAdded(book.isbn)) {
            System.out.println("Operacja nieudana - książka o takim numerze ISBN już istnieje");
        } else {
            FileHelper.addObjectToFile(BOOKS_TXT, book.toString());
        }

        return book;
    }

    public static void rentBook(User user, String isbn) throws FileNotFoundException {
        boolean shouldAdd = false;
        if (isAdded(isbn)) return;
        try (Scanner scanner = new Scanner(new File(BOOKS_TXT))) {
            while (scanner.hasNextLine()) {
                final String lineFromFile = scanner.nextLine().toLowerCase();
                if (lineFromFile.contains(user.getEmail()) && lineFromFile.contains(isbn)) {
                    System.out.println("Ten użytkownik ma już tą książkę");
                }
                if (lineFromFile.contains("isbn='" + isbn) && lineFromFile.contains("free=0")) {
                    System.out.println("Brak książki na stanie!");
                }

            }
        }
        if (shouldAdd){
            user.addBook(isbn);
            try (Scanner scanner = new Scanner(new File(BOOKS_TXT))) {
                while (scanner.hasNextLine()) {
                    final String lineFromFile = scanner.nextLine().toLowerCase();
                    if (lineFromFile.contains(user.getEmail())) {
                    }
                }
            }
        }
    }

    private static boolean isAdded(String isbn) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(BOOKS_TXT))) {
            while (scanner.hasNextLine()) {
                final String lineFromFile = scanner.nextLine().toLowerCase();
                if (lineFromFile.contains(isbn)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("author='" + author + "'")
                .add("isbn='" + isbn + "'")
                .add("yearOfRelease=" + yearOfRelease)
                .add("isAvailable=" + isAvailable)
                .add("count=" + count)
                .add("free=" + free)
                .toString();
    }
}

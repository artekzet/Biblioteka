package com.biblioteka;

import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private final int yearOfRelease;
    private final boolean isAvailable;
    private final int count;
    private int free;

    public Book(String title, String author, String isbn, int yearOfRelease, boolean isAvailable, int count) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.yearOfRelease = yearOfRelease;
        this.isAvailable = isAvailable;
        this.count = count;
        this.free = count;
    }

    public static Book generateBook() {
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
        return new Book(title, surname, isbn, year, true, count);
    }

    public static boolean isAdded(String isbn, List<Book> books) {
        for (Book b : books) {
            if (b.isbn.contains(isbn)) {
                return true;
            }
        }

        return false;
    }

    public static Book getBookByIsbn(String isbn, List<Book> books) throws BookNotFount {
        for (Book b : books) {
            if (b.getIsbn().contains(isbn)) {
                return b;
            }
        }
        throw new BookNotFount("There is no book with isbn: " + isbn);
    }

    public void rentBook(User user, String isbn, List<Book> books) {
        if (!isAdded(isbn, books)) {
            System.out.println("Książka nie dodana!");
        } else if (this.free <= 0) {
            System.out.println("Nie ma ksiazki na stanie!");
        } else if (user.hasBook(isbn)) {
            System.out.println("Juz masz ta ksiazke!");
        } else {
            user.addBook(isbn);
            this.free -= 1;
        }
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

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getCount() {
        return count;
    }

    public int getFree() {
        return free;
    }
}

package com.biblioteka;

import java.util.Scanner;

public class Book {
    private static final String BOOKS_TXT = "src/main/resources/books.txt";
    public String title;
    public String author;
    public String isbn;
    public int yearOfRelease;
    public boolean isAvailable;

    public Book(String title, String author, String isbn, int yearOfRelease, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.yearOfRelease = yearOfRelease;
        this.isAvailable = isAvailable;
    }

    public static void generateUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj tytul: ");
        String title = sc.next();
        System.out.println("Podaj nazwisko autora: ");
        String surname = sc.next();
        System.out.println("Podaj isbn: ");
        String isbn = sc.next();
        System.out.println("Podaj rok wydania: ");
        int year = sc.nextInt();
        Book book = new Book(title, surname, isbn, year, true);
        FileHelper.addUserToFile(BOOKS_TXT, book.toString());
    }


}

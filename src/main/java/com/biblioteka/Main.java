package com.biblioteka;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static MemorySimulator memory = MemorySimulator.getInstance();

    public static void main(String[] args) throws BookNotFount, UserNotFount {

        while (true) {
            System.out.println("Co chcesz zrobic?");
            System.out.println("1: Dodaj uzytkownika");
            System.out.println("2: Dodaj ksiazke");
            System.out.println("3: Wypozycz ksiązke");
            System.out.println("4: Wypisz wszystkich uzytkownikow");
            System.out.println("5: Wypisz wszystkie ksiazki");
            System.out.println("6: Wypisz wszystkie ksiazki uzytkownika");
            System.out.println("0: Koniec");
            Scanner sc = new Scanner(System.in);
            try {
                int option = sc.nextInt();
                if (option == 0) {
                    break;
                }
                program(option);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Wybor musi byc liczba!");
            }

        }
    }

    public static void program(int choice) throws UserNotFount, BookNotFount {

        switch (choice) {
            case 1:
                memory.users.add(User.generateUser());
                break;
            case 2:
                memory.books.add(Book.generateBook());
                break;
            case 3:
                rentABook();
                break;
            case 4:
                memory.getAllUsers();
                break;
            case 5:
                memory.getAllBooks();
            case 6:
                getBooksFromUser();
                break;
            default:
                System.out.println("Coś zepsułeś! Nie ma takiego wyboru!");

        }
    }

    private static void rentABook() throws UserNotFount, BookNotFount {
        final List<Book> books = memory.books;
        final Set<User> users = memory.users;

        System.out.println("Podaj maila: ");

        Scanner sc = new Scanner(System.in);
        var email = sc.nextLine().toLowerCase();
        if (!memory.userExists(email)) {
            System.out.println("Nie ma takiego uzytkownika!");
            return;
        }
        var isbn = sc.nextLine();
        System.out.println("Podaj ISBN ksiazki: ");
        if (!Book.isAdded(isbn, books)) {
            System.out.println("Nie ma takiej książki!");
            return;
        }
        var book = Book.getBookByIsbn(isbn, books);
        book.rentBook(User.getUserByEmail(email, users), isbn, books);
    }

    private static void getBooksFromUser() throws UserNotFount {
        System.out.println("Podaj maila: ");
        Scanner sc = new Scanner(System.in);
        var email = sc.nextLine().toLowerCase();
        var user = User.getUserByEmail(email, memory.users);
        System.out.println("ksiazki uzytkownika " + email);
        user.getTaken().forEach(isbn -> {
            try {
                var book = Book.getBookByIsbn(isbn, memory.books);
                System.out.println(book);
            } catch (BookNotFount e) {
                e.printStackTrace();
            }
        });
    }


}

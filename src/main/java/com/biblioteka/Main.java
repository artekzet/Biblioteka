package com.biblioteka;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static MemorySimulator memory = MemorySimulator.getInstance();
    public static void main(String[] args) throws FileNotFoundException {

        while (true) {
            System.out.println("Co chcesz zrobic?");
            System.out.println("1: Dodaj uzytkownika");
            System.out.println("2: Dodaj ksiazke");
            System.out.println("3: Wypozycz ksiązke");
            System.out.println("0: Koniec");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();
            if (option == 0) {
                break;
            }
            program(option);
        }
    }

    public static void program(int choice) throws FileNotFoundException {

        switch (choice) {
            case 1:
                memory.users.add(User.generateUser());
                break;
            case 2:
                memory.books.add(Book.generateBook());
                break;
            case 3:

        }
    }


}

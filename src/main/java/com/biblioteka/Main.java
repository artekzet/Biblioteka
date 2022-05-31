package com.biblioteka;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

    public static void program(int choice) {

        switch (choice) {
            case 1:
                User.generateUser();
                break;

        }
    }


}

package Inheritance.BookShop;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String author = sc.nextLine();
        String title = sc.nextLine();
        double price = Double.parseDouble(sc.nextLine());

        try {
            Book book = new Book(title, author, price);
            GoldenEditionBook goldenEditionBook = new GoldenEditionBook(title, author, price);

            System.out.println(book.toString());
            System.out.println(goldenEditionBook.toString());
        } catch (IllegalArgumentException message) {
            System.out.println(message.getMessage());
        }
    }
}

package Inheritance.BookShop;

public class Book {
    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {
        this.setTitle(title);
        this.setAuthor(author);
        this.setPrice(price);
    }

    public String getTitle() {
        return this.title;
    }

    private void setTitle(String title) {
        if (title.length() < 3) {
            throw new IllegalArgumentException("Title not valid!");
        }
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    private void setAuthor(String author) {
        String[] authorData = author.split("\\s+");
        if (authorData.length == 2 && Character.isDigit(authorData[1].charAt(0))) {
            throw new IllegalArgumentException("Author not valid!");
        }
        this.author = author;
    }

    public double getPrice() {
        return this.price;
    }

    private void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price not valid!");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Type: %s%n" +
                        "Title: %s%n" +
                        "Author: %s%n" +
                        "Price: %.1f%n",
                this.getClass().getSimpleName(),
                this.getTitle(),
                this.getAuthor(),
                this.getPrice());

    }
}

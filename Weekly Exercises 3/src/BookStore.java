import java.util.*;

abstract class Book {
    protected String isbn;
    protected String title;
    protected double price;
    protected String author;

    public Book(String isbn, String title, double price, String author) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.author = author;
    }

    public abstract void display();
}

class PrintedBook extends Book {
    private String coverType; // hardcover or paperback
    private int pages;
    private String publisher;
    private int copies;

    public PrintedBook(String isbn, String title, double price, String author,
                       String coverType, int pages, String publisher, int copies) {
        super(isbn, title, price, author);
        this.coverType = coverType;
        this.pages = pages;
        this.publisher = publisher;
        this.copies = copies;
    }

    @Override
    public void display() {
        System.out.println("[Printed] " + title + " by " + author + " (" + coverType + ", " + pages + " pages, " +
                "Publisher: " + publisher + ", Copies: " + copies + ", Price: €" + price + ")");
    }
}

class DigitalBook extends Book {
    private int sizeKB;

    public DigitalBook(String isbn, String title, double price, String author, int sizeKB) {
        super(isbn, title, price, author);
        this.sizeKB = sizeKB;
    }

    @Override
    public void display() {
        System.out.println("[Digital] " + title + " by " + author + " (Size: " + sizeKB + "KB, Price: €" + price + ")");
    }
}

class Audiobook extends Book {
    private int durationMin;
    private String narrator;

    public Audiobook(String isbn, String title, double price, String author, int durationMin, String narrator) {
        super(isbn, title, price, author);
        this.durationMin = durationMin;
        this.narrator = narrator;
    }

    @Override
    public void display() {
        System.out.println("[Audiobook] " + title + " by " + author + " (Duration: " + durationMin +
                " min, Narrator: " + narrator + ", Price: €" + price + ")");
    }
}

public class BookStore {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();

        books.add(new PrintedBook("1234567890", "Java Programming", 39.99, "John Smith", "Hardcover", 550, "O'Reilly", 10));
        books.add(new DigitalBook("0987654321", "Python Essentials", 19.99, "Jane Doe", 2048));
        books.add(new Audiobook("1122334455", "The Art of War", 14.99, "Sun Tzu", 120, "Morgan Freeman"));

        System.out.println("List of books in the store:");
        for (Book b : books) {
            b.display();
        }
    }
}

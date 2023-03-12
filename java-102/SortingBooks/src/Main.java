import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Set<Book> books = new TreeSet<>();
        books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954, 1178));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", 1937, 310));
        books.add(new Book("The Silmarillion", "J.R.R. Tolkien", 1977, 429));
        books.add(new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979, 215));
        books.add(new Book("1984", "George Orwell", 1949, 320));

        for (Book book : books) {
            System.out.println(book.getBookName());
        }

        System.out.println("--------------------");

        Set<Book> books2 = new TreeSet<>(new Comparator<Book>() {

            public int compare(Book o1, Book o2) {
                return o1.getNumberOfPages() - o2.getNumberOfPages();
            }
        });
        books2.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954, 1178));
        books2.add(new Book("The Hobbit", "J.R.R. Tolkien", 1937, 310));
        books2.add(new Book("The Silmarillion", "J.R.R. Tolkien", 1977, 429));
        books2.add(new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979, 215));
        books2.add(new Book("1984", "George Orwell", 1949, 320));

        for (Book book : books2) {
            System.out.println(book.getBookName());
        }
    }
}
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book("The Lord of the Rings", 1178, 1954, "J.R.R. Tolkien"));
        books.add(new Book("The Hobbit", 310, 1937, "J.R.R. Tolkien"));
        books.add(new Book("Harry Potter and the Philosopher's Stone", 223, 1997, "J.K. Rowling"));
        books.add(new Book("1984", 328, 1949, "George Orwell"));
        books.add(new Book("Animal Farm", 112, 1945, "George Orwell"));
        books.add(new Book("The Hitchhiker's Guide to the Galaxy", 215, 1979, "Douglas Adams"));
        books.add(new Book("The Great Gatsby", 80, 1925, "F. Scott Fitzgerald"));
        books.add(new Book("The Catcher in the Rye", 277, 1951, "J.D. Salinger"));
        books.add(new Book("To Kill a Mockingbird", 281, 1960, "Harper Lee"));
        books.add(new Book("Pride and Prejudice", 279, 1813, "Jane Austen"));

        Map<String,String> list = books.stream().map(book -> book.getBookName() + " - " + book.getBookAuthor()).collect(Collectors.toMap(book -> book, book -> book));
        list.forEach((key, value) -> System.out.println(key + " - " + value));

        System.out.println("##################################################");

        Stream<Book> newList = books.stream().filter(book -> book.getBookPages() > 100);

        newList.forEach(book -> System.out.println(book.getBookName() + " - " + book.getBookPages()));

    }
}
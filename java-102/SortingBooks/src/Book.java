public class Book implements Comparable<Book> {
    private String bookName;
    private String authorName;
    private int yearPublished;
    private int numberOfPages;

    @Override
    public int compareTo(Book o) {
        return this.bookName.compareTo(o.bookName);
    }

    public Book(String bookName, String authorName, int yearPublished, int numberOfPages) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.yearPublished = yearPublished;
        this.numberOfPages = numberOfPages;
    }

    public String getBookName() {
        return bookName;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}

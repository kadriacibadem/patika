public class Book {
    private String bookName;
    private int bookPages;
    private int bookYear;
    private String bookAuthor;

    public Book(String name, int pages, int year, String author) {
        this.bookName = name;
        this.bookPages = pages;
        this.bookYear = year;
        this.bookAuthor = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookPages() {
        return bookPages;
    }

    public void setBookPages(int bookPages) {
        this.bookPages = bookPages;
    }

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
}

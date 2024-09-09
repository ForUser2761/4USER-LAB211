package business_layer.entity;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private int publicationYear;
    private String publisher;
    private String isbn;
    private boolean activeBook;

    public Book() {
    }

    public Book(String bookId, String title, String author, int publicationYear, String publisher, String isbn,
            boolean activeBook) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.isbn = isbn;
        this.activeBook = activeBook;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isActiveBook() {
        return activeBook;
    }

    public void setActiveBook(boolean activeBook) {
        this.activeBook = activeBook;
    }
    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s", bookId, title, author, publicationYear, publisher, isbn, activeBook ? "Active" : "Inactive");
    }

    public String getBookProperties() {
        return bookId + " | " + title + " | " + author + " | " + publicationYear + " | " + publisher + " | " + isbn
                + " | " + activeBook;
    }
}

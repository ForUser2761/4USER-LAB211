package business_layer.implement;

import java.util.List;

import business_layer.ControllerInterface;
import business_layer.entity.Book;
import business_layer.validate.CommonRegex;
import business_layer.validate.Validate;
import data_layer.implement.BookDAO;

public class BookController implements ControllerInterface<Book> {
    private final BookDAO bookDao;

    public BookController() {
        bookDao = new BookDAO();
    }

    @Override
    public void input() {
        while (true) {
            System.out.println("===================== Add Book =====================");
            String bookId = Validate.getString("Enter Book ID: ", "Book ID cannot be empty", CommonRegex.STRING_REGEX);
            if (bookDao.getBookByID(bookId) != null) {
                System.out.println("Book ID already exists. Please try again.");
                continue;
            }
            String title = Validate.getString("Enter book title: ", "Invalid title. Please try again.",
                    CommonRegex.BOOK_TITLE_REGEX);
            String author = Validate.getString("Enter book author: ", "Invalid author name. Please try again.",
                    CommonRegex.BOOK_AUTHOR_REGEX);
            int publicationYear = Validate.getInteger("Enter publication year: ",
                    "Invalid publication year. Please enter a positive integer.", 1900, 2100);
            String publisher = Validate.getString("Enter publisher: ", "Invalid publisher. Please try again.",
                    CommonRegex.STRING_REGEX);
            String isbn = Validate.getString("Enter ISBN: ", "Invalid ISBN. Please try again.",
                    CommonRegex.STRING_REGEX);
            Book newBook = new Book(bookId, title, author, publicationYear, publisher, isbn, true);
            try {
                bookDao.insert(newBook);
                System.out.println("Book added successfully.");
            } catch (Exception ex) {
                System.out.println("Error adding book: " + ex.getMessage());
            }
            if (!Validate.getBoolean("Do you want to add another book? (true/false)", "Input must be true or false")) {
                break;
            }
        }
    }

    @Override
    public void showAll() {
        try {
            System.out.println("===================== All Books =====================");
            System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s", "Book ID", "Title", "Author",
                    "Publication Year", "Publisher", "ISBN", "Status"));
            for (Book book : bookDao.getListBook()) {
                System.out.println(book);
            }
        } catch (Exception e) {
            System.out.println("Error displaying all books: " + e.getMessage());
        }
    }

    @Override
    public void delete() {
        while (true) {
            System.out.println("===================== Delete a Book =====================");
            String bookId = Validate.getString("Enter Book ID: ", "Book ID cannot be empty", CommonRegex.STRING_REGEX);
            Book book = bookDao.getBookByID(bookId);
            if (book == null) {
                System.out.println("Book not found.");
                if (!Validate.getBoolean("Do you want to try again? (true/false)", "Input must be true or false")) {
                    break;
                }
                continue;
            }
            System.out.println("Book found: " + book);
            if (Validate.getBoolean("Do you want to delete this book? (true/false)", "Input must be true or false")) {
                try {
                    bookDao.deleteBook(bookId);
                    System.out.println("Book deleted successfully.");
                } catch (Exception e) {
                    System.out.println("Error deleting book: " + e.getMessage());
                }
            }
            if (!Validate.getBoolean("Do you want to delete another book? (true/false)",
                    "Input must be true or false")) {
                break;
            }
        }
    }

    public void update() {
        while (true) {
            System.out.println("===================== Update Book Information =====================");
            String bookId = Validate.getString("Enter Book ID: ", "Book ID cannot be empty", CommonRegex.STRING_REGEX);
            Book book = bookDao.getBookByID(bookId);
            if (book == null) {
                System.out.println("Book not found.");
                if (!Validate.getBoolean("Do you want to try again? (true/false)", "Input must be true or false")) {
                    break;
                }
                continue;
            }
            System.out.println("Current Book Information:");
            System.out.println(book);
            String title = Validate.getString("Enter new book title (leave blank to keep existing): ",
                    "Invalid title. Please try again.", CommonRegex.BOOK_TITLE_REGEX);
            if (!title.isEmpty()) {
                book.setTitle(title);
            }
            String author = Validate.getString("Enter new book author (leave blank to keep existing): ",
                    "Invalid author name. Please try again.", CommonRegex.BOOK_AUTHOR_REGEX);
            if (!author.isEmpty()) {
                book.setAuthor(author);
            }
            int publicationYear = Validate.getInteger("Enter new publication year (leave blank to keep existing): ",
                    "Invalid publication year. Please enter a positive integer.", 1900, 2100);
            if (publicationYear != 0) {
                book.setPublicationYear(publicationYear);
            }
            String publisher = Validate.getString("Enter new publisher (leave blank to keep existing): ",
                    "Invalid publisher. Please try again.", CommonRegex.STRING_REGEX);
            if (!publisher.isEmpty()) {
                book.setPublisher(publisher);
            }
            String isbn = Validate.getString("Enter new ISBN (leave blank to keep existing): ",
                    "Invalid ISBN. Please try again.", CommonRegex.STRING_REGEX);
            if (!isbn.isEmpty()) {
                book.setIsbn(isbn);
            }
            try {
                bookDao.updateBook(book);
                System.out.println("Book information updated successfully.");
            } catch (Exception ex) {
                System.out.println("Error updating book information: " + ex.getMessage());
            }
            if (!Validate.getBoolean("Do you want to update another book? (true/false)",
                    "Input must be true or false")) {
                break;
            }
        }
    }

    public List<Book> getListBook() {
        return bookDao.getListBook();
    }

}

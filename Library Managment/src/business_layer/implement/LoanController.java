package business_layer.implement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import business_layer.ControllerInterface;
import business_layer.entity.Book;
import business_layer.entity.Loan;
import business_layer.entity.User;
import business_layer.validate.CommonRegex;
import business_layer.validate.Validate;
import data_layer.implement.BookDAO;
import data_layer.implement.LoanDAO;
import data_layer.implement.UserDAO;

public class LoanController implements ControllerInterface<Loan> {
    private final LoanDAO loanDao;
    private final BookDAO bookDao;
    private final UserDAO userDao;

    public LoanController() {
        loanDao = new LoanDAO();
        bookDao = new BookDAO();
        userDao = new UserDAO();
    }

    @Override
    public void input() {
        while (true) {
            System.out.println("===================== Borrow a Book =====================");
            String userId = Validate.getString("Enter User ID: ", "User ID cannot be empty", CommonRegex.STRING_REGEX);
            User user = userDao.getUserByID(userId);
            if (user == null) {
                System.out.println("User not found.");
                if (!Validate.getBoolean("Do you want to try again? (true/false)", "Input must be true or false")) {
                    return;
                }
                continue;
            }

            String bookId = Validate.getString("Enter Book ID: ", "Book ID cannot be empty", CommonRegex.STRING_REGEX);
            Book book = bookDao.getBookByID(bookId);
            if (book == null) {
                System.out.println("Book not found.");
                if (!Validate.getBoolean("Do you want to try again? (true/false)", "Input must be true or false")) {
                    return;
                }
                continue;
            }

            if (!book.isActiveBook()) {
                System.out.println("Book is not available.");
                if (!Validate.getBoolean("Do you want to try again? (true/false)", "Input must be true or false")) {
                    return;
                }
                continue;
            }

            String transactionId = Validate.getString("Enter Transaction ID: ", "Transaction ID cannot be empty", CommonRegex.STRING_REGEX);
            String borrowDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            String returnDate = Validate.getString("Enter expected return date (MM/dd/yyyy): ", "Invalid date format. Please try again.", CommonRegex.DATE_REGEX);

            Loan newLoan = new Loan(transactionId, bookId, userId, borrowDate, returnDate);
            try {
                loanDao.insert(newLoan);
                book.setActiveBook(false);
                bookDao.updateBook(book);
                System.out.println("Book borrowed successfully.");
            } catch (Exception ex) {
                System.out.println("Error borrowing book: " + ex.getMessage());
            }

            if (!Validate.getBoolean("Do you want to borrow another book? (true/false)", "Input must be true or false")) {
                break;
            }
        }
    }

    @Override
    public void showAll() {
        try {
            System.out.println("===================== All Borrowed Books =====================");
            System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s", "Transaction ID", "Book ID", "User ID", "Borrow Date", "Return Date"));
            for (Loan loan : loanDao.getListLoan()) {
                System.out.println(loan);
            }
        } catch (Exception e) {
            System.out.println("Error displaying all borrowed books: " + e.getMessage());
        }
    }

    @Override
    public void delete() {
        while (true) {
            System.out.println("===================== Return a Book =====================");
            String transactionId = Validate.getString("Enter Transaction ID: ", "Transaction ID cannot be empty", CommonRegex.STRING_REGEX);
            Loan loan = loanDao.getLoanByTransactionID(transactionId);
            if (loan == null) {
                System.out.println("Loan transaction not found.");
                if (!Validate.getBoolean("Do you want to try again? (true/false)", "Input must be true or false")) {
                    break;
                }
                continue;
            }

            Book book = bookDao.getBookByID(loan.getBookId());
            if (book == null) {
                System.out.println("Book not found.");
                if (!Validate.getBoolean("Do you want to try again? (true/false)", "Input must be true or false")) {
                    break;
                }
                continue;
            }

            System.out.println("Loan transaction found: " + loan);
            if (Validate.getBoolean("Do you want to return this book? (true/false)", "Input must be true or false")) {
                try {
                    loanDao.deleteLoan(transactionId);
                    book.setActiveBook(true);
                    bookDao.updateBook(book);
                    System.out.println("Book returned successfully.");
                } catch (Exception e) {
                    System.out.println("Error returning book: " + e.getMessage());
                }
            }

            if (!Validate.getBoolean("Do you want to return another book? (true/false)", "Input must be true or false")) {
                break;
            }
        }
    }

    public void update() {
        while (true) {
            System.out.println("===================== Update Loan Information =====================");
            String transactionId = Validate.getString("Enter Transaction ID: ", "Transaction ID cannot be empty", CommonRegex.STRING_REGEX);
            Loan loan = loanDao.getLoanByTransactionID(transactionId);
            if (loan == null) {
                System.out.println("Loan transaction not found.");
                if (!Validate.getBoolean("Do you want to try again? (true/false)", "Input must be true or false")) {
                    break;
                }
                continue;
            }

            System.out.println("Current Loan Information:");
            System.out.println(loan);
            String returnDate = Validate.getString("Enter new return date (MM/dd/yyyy) (leave blank to keep existing): ",
                    "Invalid date format. Please try again.", CommonRegex.DATE_REGEX);
            if (!returnDate.isEmpty()) {
                loan.setReturnDate(returnDate);
            }

            try {
                loanDao.updateLoan(loan);
                System.out.println("Loan information updated successfully.");
            } catch (Exception ex) {
                System.out.println("Error updating loan information: " + ex.getMessage());
            }

            if (!Validate.getBoolean("Do you want to update another loan? (true/false)", "Input must be true or false")) {
                break;
            }
        }
    }
}

package ui.implement;

import business_layer.entity.Book;
import business_layer.entity.Loan;
import business_layer.implement.BookController;
import business_layer.implement.LoanController;
import business_layer.validate.Validate;
import ui.IMenu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportMenu implements IMenu {

    @Override
    public void addItem(String item) {
        throw new UnsupportedOperationException("Unimplemented method 'addItem'");
    }

    @Override
    public int getChoice() {
        return Validate.getInteger("Enter your choice: ", "Choice must be digits", 0, 3);
    }

    @Override
    public void showMenu() {
        System.out.println("======================== Reports ========================");
        System.out.println("1. Report on Borrowed Books");
        System.out.println("2. Report on Overdue Books");
        System.out.println("3. Report on Total Borrowing Activities");
        System.out.println("0. Back to Main Menu");
    }

    @Override
    public void addOptions() {
        throw new UnsupportedOperationException("Unimplemented method 'addOptions'");
    }

    @Override
    public void clearOptions() {
        throw new UnsupportedOperationException("Unimplemented method 'clearOptions'");
    }

    public void run() {
        BookController bookController = new BookController();
        LoanController loanController = new LoanController();
        while (true) {
            showMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    reportBorrowedBooks(bookController, loanController);
                    break;
                case 2:
                    reportOverdueBooks(loanController);
                    break;
                case 3:
                    reportBorrowingActivities(loanController);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void reportBorrowedBooks(BookController bookController, LoanController loanController) {
        try {
            System.out.println("===================== Report on Borrowed Books =====================");
            System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s", "Book ID", "Title", "Author",
                    "Publication Year", "Publisher", "ISBN", "Status"));
            for (Book book : bookController.getListBook()) {
                if (!book.isActiveBook()) {
                    System.out.println(book);
                }
            }
        } catch (Exception e) {
            System.out.println("Error generating borrowed books report: " + e.getMessage());
        }
    }

    private void reportOverdueBooks(LoanController loanController) {
        try {
            System.out.println("===================== Report on Overdue Books =====================");
            System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s", "Transaction ID", "Book ID", "User ID", "Borrow Date", "Return Date"));
            List<Loan> loans = loanController.getListLoan();
            for (Loan loan : loans) {
                LocalDate borrowDate = LocalDate.parse(loan.getBorrowDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                LocalDate returnDate = LocalDate.parse(loan.getReturnDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                if (LocalDate.now().isAfter(returnDate)) {
                    System.out.println(loan);
                }
            }
        } catch (Exception e) {
            System.out.println("Error generating overdue books report: " + e.getMessage());
        }
    }

    private void reportBorrowingActivities(LoanController loanController) {
        try {
            System.out.println("===================== Report on Total Borrowing Activities =====================");
            System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s", "Transaction ID", "Book ID", "User ID", "Borrow Date", "Return Date"));
            for (Loan loan : loanController.getListLoan()) {
                System.out.println(loan);
            }
        } catch (Exception e) {
            System.out.println("Error generating borrowing activities report: " + e.getMessage());
        }
    }
}

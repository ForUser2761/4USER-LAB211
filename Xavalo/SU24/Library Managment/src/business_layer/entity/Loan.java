package business_layer.entity;

public class Loan {
    private String transactionId;
    private String bookId;
    private String userId;
    private String borrowDate;  
    private String returnDate;  

    // Constructors
    public Loan() {
    }

    public Loan(String transactionId, String bookId, String userId, String borrowDate, String returnDate) {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.userId = userId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getLoanProperties() {
        return transactionId + "|" + bookId + "|" + userId + "|" + borrowDate + "|" + returnDate;
    }

    // toString Method
    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-15s %-15s", transactionId, bookId, userId, borrowDate, returnDate);
    }
}

package data_layer.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import business_layer.entity.Loan;
import data_layer.IGenericDAO;

public class LoanDAO implements IGenericDAO<Loan> {
    private List<Loan> listLoan;

    public LoanDAO() {
        listLoan = new ArrayList<>();
    }

    @Override
    public void loadDataFromFile() throws Exception {
        BufferedReader reader = null;
        try {
            File file = new File(LOAN_FILE);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("New loan file created: " + LOAN_FILE);
            }
            reader = new BufferedReader(new FileReader(LOAN_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] loanData = line.split("\\|");
                if (loanData.length == 5) {
                    Loan loan = new Loan(loanData[0].trim(), loanData[1].trim(), loanData[2].trim(), loanData[3].trim(),
                            loanData[4].trim());
                    listLoan.add(loan);
                } else {
                    System.out.println("Incorrect loan data format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading loan data from file: " + e.getMessage());
            throw e;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing the BufferedReader: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void writeToFile() throws Exception {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(LOAN_FILE));
            for (Loan loan : listLoan) {
                writer.write(loan.getLoanProperties());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            throw e;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error closing the BufferedWriter: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void insert(Loan loan) throws Exception {
        listLoan.add(loan);
        writeToFile();
    }

    @Override
    public void clear() {
        listLoan.clear();
    }

    public Loan getLoanByTransactionID(String transactionId) {
        clear();
        try {
            loadDataFromFile();
            for (Loan loan : listLoan) {
                if (loan.getTransactionId().equals(transactionId)) {
                    return loan;
                }
            }
            // If loan is not found, return null
            return null;
        } catch (Exception e) {
            System.out.println("Error getting loan by transaction ID: " + e.getMessage());
            return null;
        }
    }

    public void deleteLoan(String transactionId) throws Exception {
        boolean found = false;
        for (int i = 0; i < listLoan.size(); i++) {
            if (listLoan.get(i).getTransactionId().equals(transactionId)) {
                listLoan.remove(i);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new Exception("Loan transaction not found.");
        }
        writeToFile();
    }

    public List<Loan> getListLoan() {
        clear();
        try {
            loadDataFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listLoan;
    }

    public void updateLoan(Loan loan) throws Exception {
        boolean found = false;
        for (int i = 0; i < listLoan.size(); i++) {
            if (listLoan.get(i).getTransactionId().equals(loan.getTransactionId())) {
                listLoan.set(i, loan);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new Exception("Loan transaction not found.");
        }
        writeToFile();
    }
}

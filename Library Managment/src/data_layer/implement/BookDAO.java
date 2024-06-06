package data_layer.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import business_layer.entity.Book;
import data_layer.IGenericDAO;

public class BookDAO implements IGenericDAO<Book> {
    private List<Book> listBook;

    public BookDAO() {
        listBook = new ArrayList<>();
    }

    @Override
    public void loadDataFromFile() throws Exception {
        BufferedReader reader = null;
        try {
            File file = new File(BOOK_FILE);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("New book file created: " + BOOK_FILE);
            }
            reader = new BufferedReader(new FileReader(BOOK_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split("\\|");
                if (bookData.length == 7) {
                    Book book = new Book(bookData[0].trim(), bookData[1].trim(), bookData[2].trim(),
                            Integer.parseInt(bookData[3].trim()), bookData[4].trim(), bookData[5].trim(),
                            Boolean.parseBoolean(bookData[6].trim()));
                    listBook.add(book);
                } else {
                    System.out.println("Incorrect book data format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading book data from file: " + e.getMessage());
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
            writer = new BufferedWriter(new FileWriter(BOOK_FILE));
            for (Book book : listBook) {
                writer.write(book.getBookProperties());
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
    public void insert(Book book) throws Exception {
        listBook.add(book);
        writeToFile();
    }

    @Override
    public void clear() {
        listBook.clear();
    }

    public Book getBookByID(String bookId) {
        clear();
        try {
            loadDataFromFile();
            for (Book book : listBook) {
                if (book.getBookId().equals(bookId)) {
                    return book;
                }
            }
            // If book is not found, return null
            return null;
        } catch (Exception e) {
            System.out.println("Error getting book by ID: " + e.getMessage());
            return null;
        }
    }

    public void deleteBook(String bookId) throws Exception {
        boolean found = false;
        for (Book book : listBook) {
            if (book.getBookId().equals(bookId)) {
                book.setActiveBook(false);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new Exception("Book not found.");
        }
        writeToFile();
    }

    public List<Book> getListBook() {
        clear();
        try {
            loadDataFromFile();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listBook;
    }

    public void updateBook(Book book) throws Exception {
        boolean found = false;
        for (int i = 0; i < listBook.size(); i++) {
            if (listBook.get(i).getBookId().equals(book.getBookId())) {
                listBook.set(i, book);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new Exception("Book not found.");
        }
        writeToFile();
    }

}

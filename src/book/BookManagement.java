package book;
import interfaces.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookManagement {
    public static final String FILE_PATH = "book.csv";
    public static final String DATA_PATH = "data.csv";
    private static BookManagement bookManagement = new BookManagement();
    public static BookManagement getBookManagement(){
        return bookManagement;
    }
    private ReadWriteable readWriteable = new BookReadWriteFile();
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public BookManagement() {
        books = new ArrayList<>();

        Book book1 = new Book("1234", "sach doi gio hu", "mai", 1998);
        Book book2 = new Book("1235", "sach giao khoa", "mia", 2000);
        Book book3 = new Book("1236", "sach bai tap", "mike", 2003);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        readFromFile();
    }

    public void add(Book b) {
        books.add(b);
        saveToFile();
    }

    public boolean remove(String ISBN) {
        Book b = searchById(ISBN);
        if (b != null) {
            books.remove(b);
            return true;
        }
        saveToFile();
        return false;
    }

    public Book searchById(String ISBN) {
        for (Book b : books) {
            if (b.getISBN().equals(ISBN)) {
                return b;
            }
        }
        return null;
    }

    public List<Book> searchByName(String title) {
        List<Book> bookArrayList = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().contains(title)) {
                bookArrayList.add(b);
            }
        }
        return bookArrayList;
    }

    public void saveToFile()  {
       /* try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Book book : books) {
                bufferedWriter.write(book.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        readWriteable.save(FILE_PATH,  books);
    }

    public void readFromFile()  {
        books.clear();
       books = readWriteable.Read(FILE_PATH);
    }


    public String display() {
        String listBook = "";
        for (Book book : books) {
            listBook += book.toString() + "\n";
        }
        return listBook;
    }

    public void update(String ISBN, Book book) {
        Book book1 = searchById(ISBN);
        if (book1 != null) {
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            book1.setYear(book.getYear());
        }
        saveToFile();
    }
}

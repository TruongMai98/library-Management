package interfaces;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import book.Book;
import book.BookManagement;

public class BookReadWriteFile implements ReadWriteable{
    BookManagement bookManagement = BookManagement.getBookManagement();
    @Override
    public void Read(String file) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        while (true) {
            try {
                if ((line = bufferedReader.readLine()) == null) {
                    break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Book book = handleLine(line);
            System.out.println(bookManagement);
            bookManagement.getBooks().add(book);
        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void save(String file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Book book : bookManagement.getBooks()) {
                bufferedWriter.write(book.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Book handleLine(String line) {
        String[] strings = line.split(",");
        return new Book(strings[0], strings[1], strings[2], Integer.parseInt(strings[3]));

    }
}

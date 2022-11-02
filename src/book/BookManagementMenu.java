package book;

import java.util.List;
import java.util.Scanner;

public class BookManagementMenu {

    BookManagement bookManagement = BookManagement.getBookManagement();
//    BookManagement bookManagement = new BookManagement();

    public void displayMenu() {
        System.out.println("=======Menu==========");
        System.out.println("*  Book Management  *");
        System.out.println("* 1. Add book       *");
        System.out.println("* 2. Remove book    *");
        System.out.println("* 3. Search by ISBN *");
        System.out.println("* 4. Search by tile *");
        System.out.println("* 5. Read  file     *");
        System.out.println("* 6. Save file      *");
        System.out.println("* 7. Display all    *");
        System.out.println("* 8. Update         *");
        System.out.println("* 0. Done           *");
        System.out.println("=====================");
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choose = -1;
        while (choose != 0) {
            displayMenu();
            System.out.println("Enter number");
            choose = scanner.nextInt();
            scanner.nextLine();
            switch (choose) {
                case 1:
                    add();
                    break;
                case 2:
                    remove();
                    break;
                case 3:
                    searchByISBN();
                    break;
                case 4:
                    searchByTitle();
                    break;
                case 5:
                    readFile();
                    break;
                case 6:
                    saveFile();
                    break;
                case 7:
                    displayAll();
                    break;
                case 8:
                    update();
                    break;
                case 0:
                    break;
                default:
                    break;
            }

        }
    }

    private void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ISBN need update");
        String ISBN = scanner.nextLine();
        System.out.println("Enter new ISBN");
        String title = scanner.nextLine();
        System.out.println("Enter new author");
        String author = scanner.nextLine();
        System.out.println("Enter new year");
        int year = scanner.nextInt();
        scanner.nextLine();

        Book newBook = new Book(ISBN, title, author, year);
        bookManagement.update(newBook.getISBN(), newBook);
    }

    private void displayAll() {
        System.out.println(bookManagement.display());
    }

    private void saveFile() {
        bookManagement.saveToFile();
    }

    private void readFile() {
        bookManagement.readFromFile();
    }

    private void searchByTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter title");
        String title = scanner.nextLine();
        List<Book> bookList = bookManagement.searchByName(title);
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    private void searchByISBN() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ISBN");
        String ISBN = scanner.nextLine();
        Book searchBook = bookManagement.searchById(ISBN);
        if (searchBook != null) {
            System.out.println(searchBook);
        } else {
            System.out.println("Not found");
        }
    }

    private void remove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ISBN");
        String ISBN = scanner.nextLine();
        if (bookManagement.remove(ISBN)) {
            System.out.println("Remove successful");
        } else {
            System.out.println("Remove fail!! Check again");
        }
    }

    private void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ISBN");
        String ISBN = scanner.nextLine();
        System.out.println("Enter title");
        String title = scanner.nextLine();
        System.out.println("Enter author");
        String author = scanner.nextLine();
        System.out.println("Enter year");
        int year = scanner.nextInt();
        scanner.nextLine();

        Book newBook = new Book(ISBN, title, author, year);
        bookManagement.add(newBook);
    }
}

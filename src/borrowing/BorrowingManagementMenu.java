package borrowing;

//import borrow.BorrowManagement;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import book.BookManagement;
import student.StudentManagement;

public class BorrowingManagementMenu {
    BorrowingManagement borrowingManagement = BorrowingManagement.getBorrowingManagement();
    BookManagement bookManagement = BookManagement.getBookManagement();
    StudentManagement studentManagement = StudentManagement.getStudentManagement();

    public void displayMenu() {
        System.out.println("==============MENU==============");
        System.out.println("*     Borrow Management        *");
        System.out.println("* 1. Add borrow                *");
        System.out.println("* 2. Return book               *");
        System.out.println("* 3. Search by borrow id       *");
        System.out.println("* 4. Search by book id         *");
        System.out.println("* 5. Search by student id      *");
        System.out.println("* 6. Read  file                *");
        System.out.println("* 7. Save file                 *");
        System.out.println("* 8. Display all               *");
        System.out.println("* 9. Get On Borrowings         *");
        System.out.println("* 10. Remove all borrowing     *");
        System.out.println("* 11. Remove by borrow id      *");
        System.out.println("* 12. The most borrow book     *");
        System.out.println("* 13. The students borrow most *");
        System.out.println("* 14. Overdue list of books    *");
        System.out.println("* 0. Done                      *");
        System.out.println("================================");
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choose = -1;
        while (choose != 0) {
            displayMenu();
            System.out.println("Enter number");
            choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    add();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    searchByBorrowingId();
                    break;
                case 4:
                    searchByBookId();
                    break;
                case 5:
                    searchByStudentId();
                    break;
                case 6:
                    readFile();
                    break;
                case 7:
                    saveFile();
                    break;
                case 8:
                    displayAll();
                    break;
                case 9:
                    getOnBorrowings();
                    break;
                case 10:
                    removeBorrowing();
                    break;
                case 11:
                    removeByIdBorrow();
                    break;
                case 12:
                    mostBorrowedBook();
                    break;
                case 13:
                    studentsBorrowBook();
                    break;
                case 14:
                    outOfDate();
                    break;
                case 99:
                    test();
                    break;
                default:
                    break;
            }
        }
    }

    private void searchByBookId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book ID:");
        String bookID = scanner.nextLine();
        List<Borrowing> searchByBookId = borrowingManagement.searchBookId(bookID);
        for (Borrowing b : searchByBookId) {
            System.out.println(b);
        }
    }

    private void searchByStudentId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student ID:");
        int studentID = scanner.nextInt();
        scanner.nextLine();
        List<Borrowing> searchStudentIdList = borrowingManagement.searchByStudentId(studentID);
        for (Borrowing b : searchStudentIdList) {
            System.out.println(b);
        }
    }

    private void outOfDate() {
        System.out.println("Out of date");
        System.out.println(borrowingManagement.overDue());
    }

    private void test() {
        try {
            borrowingManagement.test();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void studentsBorrowBook() {
        System.out.println("List of students borrow books the most");
        System.out.println(borrowingManagement.studentsBorrowMost());
    }

    private void mostBorrowedBook() {
        System.out.println("Most borrowed books list");

        System.out.println(borrowingManagement.mostBorrowedBooks());

    }

    private void removeByIdBorrow() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter borrow id remove");
        int borrowIdRemove = scanner.nextInt();
        borrowingManagement.removeBorrowing(borrowIdRemove);
    }

    private void removeBorrowing() {
        borrowingManagement.removeBorrowingAll();
    }

    private void displayAll() {
        System.out.println(borrowingManagement.display());
    }

    private void saveFile() {
        borrowingManagement.saveFile();
    }

    private void readFile() {
        borrowingManagement.readFromFile();
    }

    public void add(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner scanner = new Scanner(System.in);
        
        int studentID = 0;
        while (true) {
            System.out.println("Enter Student ID: ");
            studentID = scanner.nextInt();
            if (studentManagement.searchById(studentID) == null ) {
                System.out.println("Student id not found");
            } else {
                break;
            }
        }
        scanner.nextLine();

        String bookID;
        do {
            System.out.println("Enter book ID");
            bookID = scanner.nextLine();
            if (bookManagement.searchById(bookID) == null) {
                System.out.println("Book id not found");
            }
        } while (bookManagement.searchById(bookID) == null);


        System.out.println("Enter date borrow");
        LocalDate dateBorrow = LocalDate.parse(scanner.nextLine(), formatter);

        Borrowing br = new Borrowing(studentID,bookID,dateBorrow);
        borrowingManagement.add(br);
        System.out.println(br.getBorrowId());

    }

    public void returnBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter borrowing ID:");
        int borrowingID = scanner.nextInt();scanner.nextLine();
        borrowingManagement.returnBook(borrowingID);

    }

    public void searchByBorrowingId(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter borrowing ID:");
        int borrowingID = scanner.nextInt();scanner.nextLine();
        Borrowing br = borrowingManagement.searchByBorrowingId(borrowingID);
        System.out.println(br);
    }

    public void getOnBorrowings(){
        List<Borrowing> onBorrowings = borrowingManagement.getOnBorrowings();
        System.out.println("Unpaid loan list");
        for( Borrowing br : onBorrowings){
            System.out.println(br);
        }
    }
}

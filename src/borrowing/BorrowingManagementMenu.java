package borrowing;

//import borrow.BorrowManagement;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class BorrowingManagementMenu {
    BorrowingManagement borrowingManagement = new BorrowingManagement();

    public void displayMenu() {
        System.out.println("==============MENU==============");
        System.out.println("*     Borrow Management        *");
        System.out.println("* 1. Add borrow                *");
        System.out.println("* 2. Return book               *");
        System.out.println("* 3. Search by borrow id       *");
//        System.out.println("* 4. Search by book id         *");
//        System.out.println("* 5. Search by student id      *");
        System.out.println("* 6. Read  file                *");
        System.out.println("* 7. Save file                 *");
        System.out.println("* 8. Display all               *");
        System.out.println("* 9. Get On Borrowings         *");
        System.out.println("* 10. Remove all borrowing     *");
        System.out.println("* 11. Remove by borrow id      *");
        System.out.println("* 12. The most borrow book     *");
        System.out.println("* 12. The students borrow most *");
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
                case 99:
                    test();
                    break;
                default:
                    break;
            }
        }
    }

    private void studentsBorrowBook() {
        System.out.println("List of students borrow books the most");
        System.out.println(borrowingManagement.studentsBorrowMost());
    }

    private void test() {
        try {
            borrowingManagement.test();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        try {
            borrowingManagement.saveFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFile() {
        try {
            borrowingManagement.readFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Student ID: ");
        int studentID = scanner.nextInt();scanner.nextLine();
        System.out.println("Enter book ID");
        String bookID = scanner.nextLine();
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
        for( Borrowing br : onBorrowings){
            System.out.println(br);
        }
    }
}

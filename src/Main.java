import book.BookManagementMenu;
import borrowing.BorrowingManagement;
import borrowing.BorrowingManagementMenu;
import student.StudentManagementMenu;

public class Main {
    public static void main(String[] args) {

//        new BookManagementMenu().menu();
//        new StudentManagementMenu().menu();
//        new BookManagementMenu().menu();
        BorrowingManagementMenu borrowingManagementMenu = new BorrowingManagementMenu();
        borrowingManagementMenu.menu();
    }
}
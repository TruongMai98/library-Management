import java.util.Scanner;

import book.BookManagementMenu;
import borrowing.BorrowingManagement;
import borrowing.BorrowingManagementMenu;
import student.StudentManagementMenu;

public class Main {
    public static void main(String[] args) {
        System.out.println("1. Book management");
        System.out.println("2. Student management");
        System.out.println("3. Borrow management");
        System.out.println("0. Exit");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        scanner.nextLine();
        if (choose == 1) {
            new BookManagementMenu().menu();
        } else if (choose == 2) {
            new StudentManagementMenu().menu();
        } else if (choose == 3) {
            new BorrowingManagementMenu().menu();
        }else {
            System.exit(0);
        }
    }
}
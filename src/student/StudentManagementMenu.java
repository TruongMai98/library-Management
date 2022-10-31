package student;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StudentManagementMenu {
    StudentManagement studentManagement = StudentManagement.getStudentManagement();
    public void displayMenu() {
        System.out.println("=======Menu==========");
        System.out.println("* Student Management*");
        System.out.println("* 1. Add student    *");
        System.out.println("* 2. Remove student *");
        System.out.println("* 3. Search by id   *");
        System.out.println("* 4. Search by name *");
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
                    searchById();
                    break;
                case 4:
                    searchByName();
                    break;
                case 5:
                    readFile();
                    break;
                case 6:
                    saveFile();
                    break;
                case  7:
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
        System.out.println("Enter id need update");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new name");
        String name = scanner.nextLine();

        Student newStudent = new Student(id, name);
        studentManagement.update(newStudent.getId(), newStudent);
    }

    private void displayAll() {
        System.out.println(studentManagement.display());
    }

    private void saveFile() {
        studentManagement.saveToFile();
    }

    private void readFile() {
        studentManagement.readFormFile();
    }

    private void searchByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scanner.nextLine();
        List<Student> studentList = studentManagement.searchByName(name);
        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    private void searchById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id");
        int id = scanner.nextInt();
        Student studentSearchById = studentManagement.searchById(id);
        if (studentSearchById != null) {
            System.out.println(studentSearchById);
        } else {
            System.out.println("Not found");
        }
    }

    private void remove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id");
        int id = scanner.nextInt();
        if (studentManagement.remove(id)) {
            System.out.println("Remove successful");
        } else {
            System.out.println("Remove fail!!! Check again!");
        }
    }

    private void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter name");
        String name = scanner.nextLine();

        Student newStudent = new Student(id, name);
        studentManagement.add(newStudent);
    }
}

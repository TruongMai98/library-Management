package student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagement {
    public static final String FILE_PATH = "student.csv";
    private List<Student> studentList;

    public StudentManagement() {
        studentList = new ArrayList<>();
    }

    public void add(Student s){
        studentList.add(s);
    }

    public boolean remove(int id) {
        Student s = searchById(id);
        if (s != null) {
            studentList.remove(s);
            return true;
        }
        return false;
    }

    public Student searchById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public List<Student> searchByName(String name) {
        List<Student> studentList1 = new ArrayList<>();
        for (Student s : studentList ){
            if (s.getName().contains(name)) {
                studentList1.add(s);
            }
        }
        return studentList1;
    }

    public void saveToFile() throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Student s : studentList) {
                bufferedWriter.write(s.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFormFile() throws IOException{
        studentList.clear();
        try {
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                Student student = parseLine(line);
                studentList.add(student);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Student parseLine(String line) {
        String[] strings = line.split(",");
        return new Student(Integer.parseInt(strings[0]),strings[1]);
    }

    public String display() {
        String listStudent = "";
        for (Student s : studentList) {
            listStudent += s.toString() + "\n";
        }
        return listStudent;
    }

    public void update(int id, Student newStudents) {
        Student student = searchById(id);
        if (student != null) {
            student.setName(newStudents.getName());
        }
    }

}

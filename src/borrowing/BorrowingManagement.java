package borrowing;

import static java.time.temporal.ChronoUnit.DAYS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import book.BookManagement;


public class BorrowingManagement {
    private static final String FILE_PATH = "borrowing.csv";
    private static BorrowingManagement borrowingManagement = new BorrowingManagement();
    private BookManagement bookManagement = BookManagement.getBookManagement();
    public static BorrowingManagement getBorrowingManagement() {
        return borrowingManagement;
    }
    private List<Borrowing> borrowings;


    private BorrowingManagement() {
        borrowings = new ArrayList<>();
        readFromFile();
        /*Borrowing br1 = new Borrowing(1, "1235", new Date("10/25/2022"));
        Borrowing br2 = new Borrowing(2, "1234", new Date("10/15/2022"));
        Borrowing br3 = new Borrowing(3, "1236", new Date("10/20/2022"));
        Borrowing br4 = new Borrowing(3, "1235", new Date("10/22/2022"));
        Borrowing br5 = new Borrowing(2, "1235", new Date("10/21/2022"));

        borrowings.add(br1);
        borrowings.add(br2);
        borrowings.add(br3);
        borrowings.add(br4);
        borrowings.add(br5);*/
    }

    public int add(Borrowing b) {// hanh dong muon sach
        borrowings.add(b);
        saveFile();
        return b.getBorrowId(); // dua ma muon sach cho nguoi dung
    }

    public void returnBook(int borrowId) { // hanh dong tra sach
        Borrowing borrowing = searchByBorrowingId(borrowId);
        if (borrowing != null) {
            borrowing.setDateReturn(LocalDate.now());
            borrowing.setStatus(true);
        } else {
            System.out.println("Not found id borrow");;
        }
        saveFile();
    }

    public Borrowing searchByBorrowingId(int borrowId) {// tim kiem "don muon sach" bang "ma muon sach"
        for (Borrowing b : borrowings) {
            if (b.getBorrowId() == borrowId) {
                return b;
            }
        }
        return null;
    }

    public List<Borrowing> searchByStudentId(int studentId) {
        List<Borrowing> studentArray = new ArrayList<>();
        for (Borrowing b : borrowings) {
            if (b.getStudentId() == studentId) {
                studentArray.add(b);
            }
        }
        return studentArray;
    }

    public List<Borrowing> searchBookId(String bookId) {
        List<Borrowing> bookArray = new ArrayList<>();
        for (Borrowing b : borrowings) {
            if (b.getBookId().contains(bookId)) {
                bookArray.add(b);
            }
        }
        return bookArray;
    }

    public List<Borrowing> getOnBorrowings() { // danh sach sach "chua" tra
        List<Borrowing> onBorrowings = new ArrayList<>();
        for (Borrowing br : borrowings) {
            if (!br.isStatus()) {
                onBorrowings.add(br);
            }
        }
        return onBorrowings;
    }

    public void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Borrowing b : borrowings) {
                bufferedWriter.write(b.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void readFromFile() {
        borrowings.clear();
        try {
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                Borrowing borrowing = parseLine(line);
                borrowings.add(borrowing);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static Borrowing parseLine(String line) {
        String[] strings = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate strings3Date = LocalDate.parse(strings[3], formatter);
        LocalDate strings4Date = strings[4].equals("null") ? null : LocalDate.parse(strings[4], formatter);
        return new Borrowing(
                Integer.parseInt(strings[0]),
                Integer.parseInt(strings[1]),
                strings[2], strings3Date,
                strings4Date,
                Boolean.parseBoolean(strings[5]));
    }

    public String display() {
        String listBorrow = "";
        for (Borrowing b : borrowings) {
            listBorrow += b.toString() + "\n";
        }
        return listBorrow;
    }

    public void removeBorrowingAll() {
        borrowings.removeAll(borrowings);
        saveFile();
    }

    public boolean removeBorrowing(int borrowId) {
        Borrowing borrowingRemove = searchByBorrowingId(borrowId);
        if (borrowingRemove != null) {
            borrowings.remove(borrowingRemove);
            return true;
        }
        saveFile();
        return false;
    }

    public String mostBorrowedBooks() {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (Borrowing br : borrowings) {
            int key = Integer.parseInt(br.getBookId());
            if (hashMap.containsKey(key) == false) {
                hashMap.put(key,1);
            } else {
                int value = hashMap.get(key);
                value++;
                hashMap.put(key, value);
            }
        }

        int max = Collections.max(hashMap.values());
        String str ="";
        for (Map.Entry<Integer, Integer> maps : hashMap.entrySet()) {
            if (maps.getValue() == max) {
                str += bookManagement.searchById(String.valueOf(maps.getKey())) + "\n";
            }
        }
        str += "Borrowed Times: " + max + " times\n";
        return str;
//        int max = 0;
//        int result = 0;
//        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
//             if (entry.getValue() > max) {
//                 max = entry.getValue() ;
//                 result = entry.getKey();
//             }
//        }

//        return "Book id: " + result + " time: " + max + "\n";
       /* Map<Integer, Integer> sortedMap = getSortedMap(hashMap);
        String string = "";
        for (Map.Entry entry : sortedMap.entrySet()) {
            string += "Book id: " + entry.getKey() + " time: " +entry.getValue() + "\n";
        }
        return string;
        *//*List<String> arrays = new ArrayList<>();
        for (Map.Entry entry : sortedMap.entrySet()) {
            arrays.add("Book id " + entry.getKey() + " time " +entry.getValue() + "\n");
        }
//        return arrays;*/
    }

    public String studentsBorrowMost() {
        // hash map
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (Borrowing br : borrowings) {
            int key = br.getStudentId();
            if (hashMap.containsKey(key) == false) {
                hashMap.put(key, 1);
            } else {
                int value = hashMap.get(key);
                value++;
                hashMap.put(key, value);
            }
        }
        /*// khoi tao mot set entry
        Set<Map.Entry<Integer, Integer>> entrySet = hashMap.entrySet();
        // tao comparator
        Comparator<Map.Entry> comparator = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                if ((Integer) o1.getValue() > (Integer) o2.getValue()) {
                    return -1;
                } else if ((Integer) o1.getValue() < (Integer) o2.getValue()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        //covert Set thanh List
        List<Map.Entry> entryList = new ArrayList<>(entrySet);
        // sap xep list
        Collections.sort(entryList, comparator);
        //tao mot hashmap va put cac entry tu entryList da sap xep sang
        Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry entry : entryList) {
            sortedMap.put((Integer) entry.getKey(), (Integer) entry.getValue());
        }
        // hien thi danh sach da sap xep*/
        Map<Integer, Integer> sortedMap = getSortedMap(hashMap);
        String string = "";
        for (Map.Entry entry : sortedMap.entrySet()) {
            string += "Student id: " + entry.getKey() + " times: " +entry.getValue() + "\n";
        }
        saveFile();
        return string;
        /*List<String> arrays = new ArrayList<>();
        for (Map.Entry entry : sortedMap.entrySet()) {
            arrays.add("Student id " + entry.getKey() + " times " +entry.getValue() + "\n");
        }
        return arrays;*/
    }

    private static Map<Integer, Integer> getSortedMap(Map<Integer, Integer> hashMap) {
        // khoi tao mot set entry
        Set<Map.Entry<Integer, Integer>> entrySet = hashMap.entrySet();
        // tao comparator
        Comparator<Map.Entry> comparator = new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                if ((Integer) o1.getValue() > (Integer) o2.getValue()) {
                    return -1;
                } else if ((Integer) o1.getValue() < (Integer) o2.getValue()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        //covert Set thanh List
        List<Map.Entry> entryList = new ArrayList<>(entrySet);
        // sap xep list
        Collections.sort(entryList, comparator);
        //tao mot hashmap va put cac entry tu entryList da sap xep sang
        Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry entry : entryList) {
            sortedMap.put((Integer) entry.getKey(), (Integer) entry.getValue());
        }
        return sortedMap;
    }

    public String overDue() {
        //status = false
        // today - dateBorrow > 7 ngay
        List<Borrowing> overDueList = new ArrayList<>();
        for (Borrowing br : borrowings) {
            int returnDay = 0;
            if (br.getDateReturn() == null) {
                returnDay = (int) DAYS.between(br.getDateBorrow(), LocalDate.now());
            }else {
                returnDay = (int) DAYS.between(br.getDateReturn(), br.getDateBorrow());
            }
            if (!br.isStatus() && returnDay > 7) {
                overDueList.add(br);
            }
        }
        /*List<String> arrays = new ArrayList<>();
        for (Borrowing br : overDueList) {
            arrays.add(br + "\n");
        }
        return arrays.toString();*/
        String string = "";
        for (Borrowing b : borrowings) {
            string += b.toString() + "\n";
        }
        return string;
    }

    public void test() throws IOException{}
    // public String -> return srs += .....
    // public List<> -> return arrayList -> menu  lap qua List<> foreach
}


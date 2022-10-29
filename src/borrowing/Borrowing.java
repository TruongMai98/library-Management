package borrowing;

import java.time.LocalDate;

public class Borrowing {
    private int borrowId;
    private int studentId;
    private String bookId;
    private LocalDate dateBorrow;
    private LocalDate dateReturn;
    private boolean status;
    private static int count = 0;

    public Borrowing() {
    }

    public Borrowing(int studentId, String bookId, LocalDate dateBorrow) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.dateBorrow = dateBorrow;
        this.borrowId = ++count;
        this.dateReturn = null;
        this.status = false;
    }

    public Borrowing(
            int borrowId, int studentId, String bookId,
            LocalDate dateBorrow, LocalDate dateReturn, boolean status) {
        this.borrowId = borrowId;
        this.studentId = studentId;
        this.bookId = bookId;
        this.dateBorrow = dateBorrow;
        this.dateReturn = dateReturn;
        this.status = status;
    }

    public int getCount() {
        return count;
    }
    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(LocalDate dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    public LocalDate getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(LocalDate dateReturn) {
        this.dateReturn = dateReturn;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return borrowId +
                "," +
                studentId +
                "," +
                bookId+
                "," +
                dateBorrow +
                "," +
                dateReturn +
                "," +
                status;
    }
}

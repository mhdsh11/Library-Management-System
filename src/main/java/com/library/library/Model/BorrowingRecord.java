package com.library.library.Model;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "BorrowingRecord")
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "bookId")
    private Integer bookId;
    @Column(name = "patronId")
    private Integer patronId;

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "id", insertable = false, updatable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patronId", referencedColumnName = "id", insertable = false, updatable = false)
    private Patron patron;

    private LocalDate borrowingDate;
    private LocalDate returnDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getPatronId() {
        return patronId;
    }

    public void setPatronId(Integer patronId) {
        this.patronId = patronId;
    }
}
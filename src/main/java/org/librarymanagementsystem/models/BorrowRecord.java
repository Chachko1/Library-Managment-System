package org.librarymanagementsystem.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Column(name ="borrowed_date")
    private LocalDate borrowedDate;
    @Column(name = "returned_date")
    private LocalDate returnedDate;

    public BorrowRecord() {

    }

    public BorrowRecord(long id, Member member, Book book, LocalDate borrowedDate, LocalDate returnedDate) {
        Id = id;
        this.member = member;
        this.book = book;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }
}

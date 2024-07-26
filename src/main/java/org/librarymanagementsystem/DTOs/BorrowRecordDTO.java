package org.librarymanagementsystem.DTOs;


import jakarta.persistence.Column;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class BorrowRecordDTO {
    private long id;

    private Long memberId;

    private String memberName;
    private Long bookId;
    private String bookName;

    private LocalDate borrowedDate;

    private LocalDate returnedDate;

    public BorrowRecordDTO() {
    }

    public BorrowRecordDTO(long id, Long memberId, String memberName, Long bookId, String bookName, LocalDate borrowedDate, LocalDate returnedDate) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.bookId = bookId;
        this.bookName = bookName;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

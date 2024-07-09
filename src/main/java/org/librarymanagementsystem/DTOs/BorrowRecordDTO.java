package org.librarymanagementsystem.DTOs;


import jakarta.persistence.Column;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class BorrowRecordDTO {
    private long id;

    private MemberDTO memberDTO;
    private BookDTO bookDTO;

    private LocalDate borrowedDate;

    private LocalDate returnedDate;

    public BorrowRecordDTO() {
    }

    public BorrowRecordDTO(long id, MemberDTO memberDTO, BookDTO bookDTO, LocalDate borrowedDate, LocalDate returnedDate) {
        this.id = id;
        this.memberDTO = memberDTO;
        this.bookDTO = bookDTO;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MemberDTO getMemberDTO() {
        return memberDTO;
    }

    public void setMemberDTO(MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
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

package org.librarymanagementsystem.DTOs;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

public class MemberDTO {
    private long id;
    @NotBlank(message = "Username cannot be empty!")
    @Length(min = 3,max = 20,message = "Username must be between 3 and 20 characters!")
    private String username;
    @NotBlank(message = "Password cannot be empty!")
    @Length(min = 3,max = 20,message = "Password must be between 3 and 20 characters!")
    private String password;

    private List<BorrowRecordDTO> borrowRecords;

    private List<ReviewDTO> reviews;

    private boolean recommendedBook;

    public MemberDTO() {
    }

    public MemberDTO(long id, String username, String password, List<BorrowRecordDTO> borrowRecords, List<ReviewDTO> reviews) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.borrowRecords = borrowRecords;
        this.reviews = reviews;
        this.recommendedBook = false;
    }

    public boolean isRecommendedBook() {
        return recommendedBook;
    }

    public void setRecommendedBook(boolean recommendedBook) {
        this.recommendedBook = recommendedBook;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BorrowRecordDTO> getBorrowRecords() {
        return borrowRecords;
    }

    public void setBorrowRecords(List<BorrowRecordDTO> borrowRecords) {
        this.borrowRecords = borrowRecords;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}

package org.librarymanagementsystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<BorrowRecord> borrowRecords;
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Member() {
    }

    public Member(long id, String username, String password) {
        Id = id;
        this.username = username;
        this.password = password;
        this.borrowRecords=new ArrayList<>();
        this.reviews=new ArrayList<>();
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
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

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }

    public void setBorrowRecords(List<BorrowRecord> borrowRecords) {
        this.borrowRecords = borrowRecords;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}

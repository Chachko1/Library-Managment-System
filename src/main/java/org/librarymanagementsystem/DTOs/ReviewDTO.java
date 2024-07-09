package org.librarymanagementsystem.DTOs;

public class ReviewDTO {

    private long id;

    private String content;

    private MemberDTO member;

    private BookDTO book;

    public ReviewDTO(long id, String content, MemberDTO member, BookDTO book) {
        this.id = id;
        this.content = content;
        this.member = member;
        this.book = book;
    }

    public ReviewDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }
}

package org.librarymanagementsystem.DTOs;

public class ReviewDTO {


    private String content;

    private MemberDTO member;

    private Long bookId;

    public ReviewDTO(String content, MemberDTO member, Long bookId) {

        this.content = content;
        this.member = member;
        this.bookId = bookId;
    }

    public ReviewDTO() {
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

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}

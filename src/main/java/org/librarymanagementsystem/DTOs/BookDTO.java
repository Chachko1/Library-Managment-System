package org.librarymanagementsystem.DTOs;

public class BookDTO {
    private long id;

    private String title;

    private String authorName;

    private String isbn;

    public BookDTO() {

    }

    public BookDTO(long id, String title, String authorName, String isbn) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.isbn = isbn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}

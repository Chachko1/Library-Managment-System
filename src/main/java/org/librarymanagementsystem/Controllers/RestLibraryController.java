package org.librarymanagementsystem.Controllers;

import org.librarymanagementsystem.DTOs.BookDTO;
import org.librarymanagementsystem.mappers.BookMapper;
import org.librarymanagementsystem.models.Book;
import org.librarymanagementsystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/library")
public class RestLibraryController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @GetMapping("/books/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(String isbn){
        BookDTO book = bookService.getBookByIsbn(isbn);

        if (book != null){
            return ResponseEntity.ok(bookMapper.toEntity(book));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook (@RequestBody Book book){
        BookDTO createdBook=bookService.saveBook(bookMapper.toDTO(book));

        return  ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.toEntity(createdBook));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook (@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();

    }
}

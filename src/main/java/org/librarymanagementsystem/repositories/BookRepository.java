package org.librarymanagementsystem.repositories;

import org.librarymanagementsystem.DTOs.BookDTO;
import org.librarymanagementsystem.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findBookById(Long id);

    Book findBookByTitle(String title);

    Book findByTitle(String title);
}

package org.librarymanagementsystem.services;

import org.librarymanagementsystem.DTOs.AuthorDTO;
import org.librarymanagementsystem.DTOs.BookDTO;
import org.librarymanagementsystem.mappers.BookMapper;
import org.librarymanagementsystem.models.Author;
import org.librarymanagementsystem.models.Book;
import org.librarymanagementsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll().stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }
    public BookDTO getBookByIsbn (String isbn){
        return bookRepository.findByIsbn(isbn).map(bookMapper::toDTO).orElse(null);
    }

    public BookDTO getBookById(Long id){
        return bookRepository.findBookById(id).map(bookMapper::toDTO).orElse(null);
    }

    public BookDTO saveBook(BookDTO bookDTO){
        Book book=bookMapper.toEntity(bookDTO);
        return bookMapper.toDTO(bookRepository.save(book));
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}

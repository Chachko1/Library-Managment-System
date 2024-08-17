package org.librarymanagementsystem.services;

import org.librarymanagementsystem.DTOs.AuthorDTO;
import org.librarymanagementsystem.DTOs.BookDTO;
import org.librarymanagementsystem.mappers.BookMapper;
import org.librarymanagementsystem.models.Author;
import org.librarymanagementsystem.models.Book;
import org.librarymanagementsystem.models.BorrowRecord;
import org.librarymanagementsystem.repositories.AuthorRepository;
import org.librarymanagementsystem.repositories.BookRepository;
import org.librarymanagementsystem.repositories.BorrowRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private ReviewService reviewService;



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
        if (bookDTO.getAuthorId() != null) {
            Author author = authorRepository.findById(bookDTO.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found"));
            book.setAuthor(author);
            book.setBorrowed(false);
        }
        return bookMapper.toDTO(bookRepository.save(book));
    }

    public void deleteBook(Long id){

        bookRepository.deleteById(id);
    }

    public Book findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public LocalDate findNextAvailableDate(Long bookId) {
        List<BorrowRecord> borrowRecords = borrowRecordRepository.findByBookId(bookId);
        LocalDate latestReturnDate = LocalDate.now();
        for (BorrowRecord record : borrowRecords) {
            if (record.getReturnedDate().isAfter(latestReturnDate)) {
                latestReturnDate = record.getReturnedDate();
            }
        }
        return latestReturnDate.plusDays(1);
    }

    // Added method to update the borrowed status of a book
    public void updateBookBorrowedStatus(Long bookId, boolean status) {
        Book book = findBookById(bookId);
        if (book != null) {
            book.setBorrowed(status);
            bookRepository.save(book);
        }
    }

    public Optional<Book> getRandomBook() {
        long count = bookRepository.count();
        if (count == 0) {
            // No books in the repository
            return Optional.empty();
        }
        int index = new Random().nextInt((int) count);
        return Optional.ofNullable(bookRepository.findAll().get(index));
    }

    @Transactional
    public void deleteBookAndReviews(Long bookId) {
        reviewService.deleteReviewByBook(bookId);
        bookRepository.deleteById(bookId);
    }
}

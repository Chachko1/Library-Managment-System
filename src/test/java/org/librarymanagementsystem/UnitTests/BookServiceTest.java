package org.librarymanagementsystem.UnitTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.librarymanagementsystem.models.Author;
import org.librarymanagementsystem.repositories.AuthorRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.librarymanagementsystem.DTOs.BookDTO;
import org.librarymanagementsystem.models.Book;
import org.librarymanagementsystem.models.BorrowRecord;
import org.librarymanagementsystem.models.Member;
import org.librarymanagementsystem.repositories.BookRepository;
import org.librarymanagementsystem.repositories.BorrowRecordRepository;
import org.librarymanagementsystem.services.BookService;
import org.librarymanagementsystem.mappers.BookMapper;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowRecordRepository borrowRecordRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookMapper bookMapper;

    private Book book;
    private BookDTO bookDTO;
    private List<BorrowRecord> borrowRecords;
    private Member member;  // Mocked or created Member instance

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Initialize test data
        member = new Member();  // Initialize Member if necessary
        book = new Book(1L, "Book Title", null, "1234567890");
        bookDTO = new BookDTO(1L, "Book Title", "Author Name", "1234567890", 1L);
        borrowRecords = Arrays.asList(
                new BorrowRecord(1L, member, book, LocalDate.now().minusDays(1), LocalDate.now())
        );
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = Arrays.asList(book);
        List<BookDTO> bookDTOs = Arrays.asList(bookDTO);

        when(bookRepository.findAll()).thenReturn(books);
        when(bookMapper.toDTO(book)).thenReturn(bookDTO);

        List<BookDTO> result = bookService.getAllBooks();

        assertEquals(bookDTOs, result);
    }

    @Test
    public void testGetBookByIsbn() {
        when(bookRepository.findByIsbn("1234567890")).thenReturn(Optional.of(book));
        when(bookMapper.toDTO(book)).thenReturn(bookDTO);

        BookDTO result = bookService.getBookByIsbn("1234567890");

        assertEquals(bookDTO, result);
    }

    @Test
    public void testGetBookById() {
        when(bookRepository.findBookById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.toDTO(book)).thenReturn(bookDTO);

        BookDTO result = bookService.getBookById(1L);

        assertEquals(bookDTO, result);
    }

    @Test
    public void testSaveBook() {
        // Arrange
        Author author = new Author(); // Create a mock or a real Author if needed
        author.setId(1L); // Set the ID to match the BookDTO authorId
        author.setName("Author Name"); // Set the name or other properties if necessary

        when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDTO(book)).thenReturn(bookDTO);
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author)); // Mock the author repository

        // Act
        BookDTO result = bookService.saveBook(bookDTO);

        // Assert
        assertEquals(bookDTO, result);
    }

    @Test
    public void testDeleteBook() {
        bookService.deleteBook(1L);

        // Verify that deleteById was called on the bookRepository
        // Using verify(bookRepository).deleteById(1L); is possible if using Mockito's verify
    }

    @Test
    public void testFindBookByTitle() {
        when(bookRepository.findByTitle("Book Title")).thenReturn(book);

        Book result = bookService.findBookByTitle("Book Title");

        assertEquals(book, result);
    }

    @Test
    public void testFindAllBooks() {
        List<Book> books = Arrays.asList(book);

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.findAllBooks();

        assertEquals(books, result);
    }

    @Test
    public void testFindBookById() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.findBookById(1L);

        assertEquals(book, result);
    }

    @Test
    public void testFindNextAvailableDate() {
        when(borrowRecordRepository.findByBookId(1L)).thenReturn(borrowRecords);

        LocalDate result = bookService.findNextAvailableDate(1L);

        assertEquals(LocalDate.now().plusDays(1), result);
    }

    @Test
    public void testUpdateBookBorrowedStatus() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        bookService.updateBookBorrowedStatus(1L, true);

        assertEquals(true, book.isBorrowed());
    }

    @Test
    public void testGetRandomBook() {
        List<Book> books = Arrays.asList(book);
        when(bookRepository.count()).thenReturn(1L);
        when(bookRepository.findAll()).thenReturn(books);

        Optional<Book> result = bookService.getRandomBook();

        assertEquals(Optional.of(book), result);
    }
}

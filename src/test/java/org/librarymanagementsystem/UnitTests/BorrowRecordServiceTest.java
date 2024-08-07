package org.librarymanagementsystem.UnitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.librarymanagementsystem.models.Book;
import org.librarymanagementsystem.models.BorrowRecord;
import org.librarymanagementsystem.models.Member;
import org.librarymanagementsystem.repositories.BorrowRecordRepository;
import org.librarymanagementsystem.services.BorrowRecordService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BorrowRecordServiceTest {

    @Mock
    private BorrowRecordRepository borrowRecordRepository;

    @InjectMocks
    private BorrowRecordService borrowRecordService;

    private BorrowRecord borrowRecord;
    private Book book;
    private Member member;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Set up a sample Member, Book, and BorrowRecord
        book = new Book(1L, "Sample Book", null, "1234567890");
        member = new Member(1L, "Sample Member", "password");
        borrowRecord = new BorrowRecord(1L, member, book, LocalDate.now().minusDays(10), LocalDate.now());
    }

    @Test
    public void testFindAllBorrowRecords() {
        List<BorrowRecord> borrowRecords = new ArrayList<>();
        borrowRecords.add(borrowRecord);

        when(borrowRecordRepository.findAll()).thenReturn(borrowRecords);

        List<BorrowRecord> result = borrowRecordService.findAllBorrowRecords();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(borrowRecord, result.get(0));
    }

    @Test
    public void testSaveBorrowRecord() {
        when(borrowRecordRepository.save(any(BorrowRecord.class))).thenReturn(borrowRecord);

        borrowRecordService.saveBorrowRecord(borrowRecord);

        verify(borrowRecordRepository, times(1)).save(borrowRecord);
    }

    @Test
    public void testFindBorrowRecordsByBookId() {
        List<BorrowRecord> borrowRecords = new ArrayList<>();
        borrowRecords.add(borrowRecord);

        when(borrowRecordRepository.findByBookId(anyLong())).thenReturn(borrowRecords);

        List<BorrowRecord> result = borrowRecordService.findBorrowRecordsByBookId(1L);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(borrowRecord, result.get(0));
    }

    @Test
    public void testFindLatestBorrowRecordByBookId() {
        when(borrowRecordRepository.findTopByBookIdOrderByReturnedDateDesc(anyLong()))
                .thenReturn(Optional.of(borrowRecord));

        Optional<BorrowRecord> result = borrowRecordService.findLatestBorrowRecordByBookId(1L);
        assertTrue(result.isPresent());
        assertEquals(borrowRecord, result.get());
    }
}


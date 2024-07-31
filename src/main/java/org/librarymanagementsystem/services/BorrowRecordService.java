package org.librarymanagementsystem.services;

import org.librarymanagementsystem.DTOs.BorrowRecordDTO;
import org.librarymanagementsystem.mappers.BorrowRecordMapper;
import org.librarymanagementsystem.models.BorrowRecord;
import org.librarymanagementsystem.repositories.BorrowRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowRecordService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    public List<BorrowRecord> findAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }

    public void saveBorrowRecord(BorrowRecord borrowRecord) {
        borrowRecordRepository.save(borrowRecord);
    }

    public List<BorrowRecord> findBorrowRecordsByBookId(long bookId) {
        return borrowRecordRepository.findByBookId(bookId);
    }

    public Optional<BorrowRecord> findLatestBorrowRecordByBookId(long bookId) {
        return borrowRecordRepository.findTopByBookIdOrderByReturnedDateDesc(bookId);
    }
}
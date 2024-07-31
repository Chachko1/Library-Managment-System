package org.librarymanagementsystem.repositories;

import org.librarymanagementsystem.models.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Long> {
    List<BorrowRecord> findByBookId(Long bookId);

    Optional<BorrowRecord> findTopByBookIdOrderByReturnedDateDesc(long bookId);
}

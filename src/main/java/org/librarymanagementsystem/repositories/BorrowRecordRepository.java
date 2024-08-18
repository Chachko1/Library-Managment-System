package org.librarymanagementsystem.repositories;

import org.librarymanagementsystem.models.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Long> {
    List<BorrowRecord> findByBookId(Long bookId);

    Optional<BorrowRecord> findTopByBookIdOrderByReturnedDateDesc(long bookId);

    @Modifying
    @Transactional
    @Query("DELETE FROM BorrowRecord br WHERE br.book.Id = :bookId")
    void deleteByBookId(@Param("bookId") Long bookId);
}

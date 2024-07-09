package org.librarymanagementsystem.repositories;

import org.librarymanagementsystem.models.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Long> {
}

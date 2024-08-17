package org.librarymanagementsystem.repositories;

import org.librarymanagementsystem.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    void deleteByBookId(Long bookId);
}

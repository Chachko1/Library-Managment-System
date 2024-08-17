package org.librarymanagementsystem.services;

import org.librarymanagementsystem.DTOs.ReviewDTO;

import org.librarymanagementsystem.mappers.ReviewMapper;
import org.librarymanagementsystem.models.Review;
import org.librarymanagementsystem.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    public void deleteReviewById(Long id) {
        reviewRepository.deleteById(id);
    }

    public void deleteReviewByBook(Long bookId) {
        reviewRepository.deleteByBookId(bookId);
    }
}

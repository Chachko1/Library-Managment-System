package org.librarymanagementsystem.UnitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.librarymanagementsystem.models.Review;
import org.librarymanagementsystem.models.Book;
import org.librarymanagementsystem.models.Member;
import org.librarymanagementsystem.repositories.ReviewRepository;
import org.librarymanagementsystem.services.ReviewService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    private Review review;
    private Book book;
    private Member member;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize entities
        member = new Member();
        member.setId(1L);
        member.setUsername("username");
        member.setPassword("password");

        book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setAuthor(null); // Adjust as necessary if you have an Author instance
        book.setIsbn("123456789");

        review = new Review();
        review.setId(1L);
        review.setContent("Great book!");
        review.setMember(member);
        review.setBook(book);
    }

    @Test
    public void testFindAllReviews() {
        when(reviewRepository.findAll()).thenReturn(Arrays.asList(review));

        List<Review> reviews = reviewService.findAllReviews();

        assertNotNull(reviews);
        assertEquals(1, reviews.size());
        assertEquals("Great book!", reviews.get(0).getContent());
    }

    @Test
    public void testSaveReview() {
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        reviewService.saveReview(review);

        verify(reviewRepository).save(review);
    }

    @Test
    public void testDeleteReviewById() {
        doNothing().when(reviewRepository).deleteById(anyLong());

        reviewService.deleteReviewById(1L);

        verify(reviewRepository).deleteById(1L);
    }
}

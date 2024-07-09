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

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<ReviewDTO> getAllReviews(){
        return reviewRepository.findAll().stream().map(reviewMapper::toDTO).collect(Collectors.toList());
    }

    public ReviewDTO saveReview(ReviewDTO reviewDTO){
        Review review=reviewMapper.toEntity(reviewDTO);
        return reviewMapper.toDTO(reviewRepository.save(review));
    }

    public void deleteReview(Long id ){
        reviewRepository.deleteById(id);
    }
}

package org.librarymanagementsystem.mappers;

import org.librarymanagementsystem.DTOs.ReviewDTO;
import org.librarymanagementsystem.models.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review toEntity(ReviewDTO reviewDTO);
    ReviewDTO toDTO(Review review);
}

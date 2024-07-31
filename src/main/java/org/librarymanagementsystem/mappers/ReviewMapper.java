package org.librarymanagementsystem.mappers;

import org.librarymanagementsystem.DTOs.MemberDTO;
import org.librarymanagementsystem.DTOs.ReviewDTO;
import org.librarymanagementsystem.models.Member;
import org.librarymanagementsystem.models.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(target = "member.reviews", ignore = true) // Ignore reviews to prevent recursion
    ReviewDTO toDTO(Review review);

    Review toEntity(ReviewDTO reviewDTO);


}

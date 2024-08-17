package org.librarymanagementsystem.mappers;

import org.librarymanagementsystem.DTOs.BorrowRecordDTO;
import org.librarymanagementsystem.DTOs.MemberDTO;
import org.librarymanagementsystem.DTOs.ReviewDTO;
import org.librarymanagementsystem.models.BorrowRecord;
import org.librarymanagementsystem.models.Member;
import org.librarymanagementsystem.models.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(source = "borrowRecords", target = "borrowRecords", qualifiedByName = "toBorrowRecordDTOList")
    @Mapping(source = "reviews", target = "reviews", qualifiedByName = "toReviewDTOList")
    MemberDTO toDTO(Member member);

    @Mapping(source = "borrowRecords", target = "borrowRecords")
    @Mapping(source = "reviews", target = "reviews")
    Member toEntity(MemberDTO memberDTO);

    @Named("toBorrowRecordDTOList")
    default List<BorrowRecordDTO> borrowRecordListToBorrowRecordDTOList(List<BorrowRecord> borrowRecords) {
        return borrowRecords.stream()
                .map(this::borrowRecordToBorrowRecordDTO)
                .toList();
    }

    @Named("toReviewDTOList")
    default List<ReviewDTO> reviewListToReviewDTOList(List<Review> reviews) {
        return reviews.stream()
                .map(this::reviewToReviewDTO)
                .toList();
    }

    @Mapping(target = "memberId", ignore = true) // Prevents recursion
    BorrowRecordDTO borrowRecordToBorrowRecordDTO(BorrowRecord borrowRecord);

    @Mapping(target = "member", ignore = true) // Prevents recursion
    ReviewDTO reviewToReviewDTO(Review review);
}

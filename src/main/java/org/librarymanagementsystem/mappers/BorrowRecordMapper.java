package org.librarymanagementsystem.mappers;

import org.librarymanagementsystem.DTOs.BorrowRecordDTO;
import org.librarymanagementsystem.models.BorrowRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BorrowRecordMapper {
    @Mappings({
            @Mapping(source = "memberId", target = "member.id"), // Ensure this mapping
            @Mapping(source = "bookId", target = "book.id")
    })
    BorrowRecord toEntity (BorrowRecordDTO borrowRecordDTO);
    @Mappings({
            @Mapping(source = "member.username", target = "memberName"),
            @Mapping(source = "member.id", target = "memberId"), // Ensure this mapping
            @Mapping(source = "book.title", target = "bookName"),
            @Mapping(source = "book.id", target = "bookId")
    })
    BorrowRecordDTO toDTO (BorrowRecord borrowRecord);
}

package org.librarymanagementsystem.mappers;

import org.librarymanagementsystem.DTOs.BorrowRecordDTO;
import org.librarymanagementsystem.models.BorrowRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BorrowRecordMapper {
    BorrowRecord toEntity (BorrowRecordDTO borrowRecordDTO);

    BorrowRecordDTO toDTO (BorrowRecord borrowRecord);
}

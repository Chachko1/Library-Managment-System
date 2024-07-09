package org.librarymanagementsystem.mappers;

import org.librarymanagementsystem.DTOs.BookDTO;
import org.librarymanagementsystem.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toDTO (Book book);
    Book toEntity (BookDTO bookDTO);

}

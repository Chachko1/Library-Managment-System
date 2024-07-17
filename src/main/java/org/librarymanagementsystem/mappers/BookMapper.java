package org.librarymanagementsystem.mappers;

import org.librarymanagementsystem.DTOs.BookDTO;
import org.librarymanagementsystem.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mappings({
            @Mapping(source = "author.name", target = "authorName"),
            @Mapping(source = "author.id", target = "authorId") // Ensure this mapping
    })
    BookDTO toDTO (Book book);
    @Mappings({
            @Mapping(source = "authorId", target = "author.id") // Ensure this mapping
    })
    Book toEntity (BookDTO bookDTO);

}

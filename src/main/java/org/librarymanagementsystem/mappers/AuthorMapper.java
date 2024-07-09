package org.librarymanagementsystem.mappers;

import org.librarymanagementsystem.DTOs.AuthorDTO;
import org.librarymanagementsystem.models.Author;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDto(Author author);

    Author toEntity(AuthorDTO dto);
}

package org.librarymanagementsystem.services;

import org.librarymanagementsystem.DTOs.AuthorDTO;
import org.librarymanagementsystem.mappers.AuthorMapper;
import org.librarymanagementsystem.models.Author;
import org.librarymanagementsystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> getAllAuthors(){
        return authorRepository.findAll().stream().map(authorMapper::toDto).collect(Collectors.toList());
    }

    public AuthorDTO saveAuthor(AuthorDTO authorDTO){
        Author author=authorMapper.toEntity(authorDTO);
        return authorMapper.toDto(authorRepository.save(author));
    }

    public void deleteAuthor(Long id){
        authorRepository.deleteById(id);
    }
}

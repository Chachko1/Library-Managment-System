package org.librarymanagementsystem.UnitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.librarymanagementsystem.DTOs.AuthorDTO;
import org.librarymanagementsystem.mappers.AuthorMapper;
import org.librarymanagementsystem.models.Author;
import org.librarymanagementsystem.repositories.AuthorRepository;
import org.librarymanagementsystem.services.AuthorService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorService authorService;

    private Author author;
    private AuthorDTO authorDTO;

    @BeforeEach
    public void setUp() {
        author = new Author(1L, "Author Name");
        authorDTO = new AuthorDTO(1L, "Author Name");
    }

    @Test
    public void testGetAllAuthors() {
        when(authorRepository.findAll()).thenReturn(Arrays.asList(author));
        when(authorMapper.toDto(any(Author.class))).thenReturn(authorDTO);

        List<AuthorDTO> authors = authorService.getAllAuthors();

        assertEquals(1, authors.size());
        assertEquals(authorDTO.getId(), authors.get(0).getId());
        verify(authorRepository, times(1)).findAll();
        verify(authorMapper, times(1)).toDto(any(Author.class));
    }

    @Test
    public void testSaveAuthor() {
        when(authorMapper.toEntity(any(AuthorDTO.class))).thenReturn(author);
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        when(authorMapper.toDto(any(Author.class))).thenReturn(authorDTO);

        AuthorDTO savedAuthor = authorService.saveAuthor(authorDTO);

        assertEquals(authorDTO.getId(), savedAuthor.getId());
        verify(authorMapper, times(1)).toEntity(any(AuthorDTO.class));
        verify(authorRepository, times(1)).save(any(Author.class));
        verify(authorMapper, times(1)).toDto(any(Author.class));
    }

    @Test
    public void testDeleteAuthor() {
        doNothing().when(authorRepository).deleteById(anyLong());

        authorService.deleteAuthor(1L);

        verify(authorRepository, times(1)).deleteById(anyLong());
    }
}

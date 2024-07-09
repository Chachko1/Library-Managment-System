package org.librarymanagementsystem.repositories;

import org.librarymanagementsystem.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {

}

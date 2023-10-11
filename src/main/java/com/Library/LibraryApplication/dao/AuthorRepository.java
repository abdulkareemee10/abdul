package com.Library.LibraryApplication.dao;

import com.Library.LibraryApplication.entity.Author;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorRepository{

    ResponseEntity<String> saveAuthor(Author author);

    ResponseEntity<String> updateAuthor(Author author);

    ResponseEntity<String> deleteById(long id);

    Author getById(long id);

    List<Author> allAuthors();
    
    Author getByName(String name);

}

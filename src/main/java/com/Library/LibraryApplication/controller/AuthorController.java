package com.Library.LibraryApplication.controller;

import com.Library.LibraryApplication.dao.AuthorRepository;
import com.Library.LibraryApplication.dao.CategoryRepository;
import com.Library.LibraryApplication.entity.Author;
import com.Library.LibraryApplication.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @PostMapping("/putauthor")
    public ResponseEntity<String> saveAuthor(@RequestBody Author author) {

        return authorRepository.saveAuthor(author);

    }

    @PutMapping("/upauthor")
    public ResponseEntity<String> updateAuthor(@RequestBody Author author) {

        return authorRepository.updateAuthor(author);

    }

    @GetMapping("/getauthor/{id}")
    public Author getAuthor(@PathVariable("id") long id) {

        return authorRepository.getById(id);

    }

    @GetMapping("/getauthors")
    public List<Author> getAuthors() {

        return authorRepository.allAuthors();


    }
    @DeleteMapping("delauthor/{id}")
    public ResponseEntity<String> deleteById( @PathVariable("id") long id) {

        return authorRepository.deleteById(id);

    }

}



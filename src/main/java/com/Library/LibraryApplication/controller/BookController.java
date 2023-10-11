package com.Library.LibraryApplication.controller;

import com.Library.LibraryApplication.dao.BookRepository;
import com.Library.LibraryApplication.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")

public class BookController {

    private static final Logger logInfo = LoggerFactory.getLogger(BookController.class);
    @Autowired
    BookRepository bookRepository;

    @PostMapping("/postbook")
    public String saveBook(@RequestBody Book book) {


        logInfo.info("book info is enabled");
        logInfo.debug("book debug logging is enabled");
        

        return bookRepository.saveBook(book);

    }

    @PutMapping("/putbook")
    public String updateBook(@RequestBody Book book) {



        return bookRepository.updateBook(book);

    }


    @GetMapping("/getbook/{id}")
    public Book getBook(@PathVariable("id") long id) {



        return bookRepository.getById(id);

    }

    @GetMapping("/getbooks")
    public List<Book> getBooks() {

        return bookRepository.allBooks();


    }
    @DeleteMapping("delbook/{id}")
    public String deleteById( @PathVariable("id") long id){

        return  bookRepository.deleteById(id);

    }
}



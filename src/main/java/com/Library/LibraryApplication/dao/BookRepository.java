package com.Library.LibraryApplication.dao;

import com.Library.LibraryApplication.entity.Book;

import java.util.List;

public interface BookRepository {
    String saveBook(Book book);

    String updateBook(Book book);

    String deleteById(long id);

    Book getById(long id);

    List<Book> allBooks();

	Book getByIsbnNameDescription(String isbn, String name, String description);
}

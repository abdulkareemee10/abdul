package com.Library.LibraryApplication.dao.impl;

import com.Library.LibraryApplication.dao.LibraryRepository;
import com.Library.LibraryApplication.entity.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibraryImpl implements LibraryRepository {

    private static final String INSERT_BOOK_QUERY = "INSERT INTO LIBRARY(id, isbn, book,  description, category,author, publisher) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_BOOK_BY_ID_QUERY ="UPDATE LIBRARY SET name=? WHERE ID=?";
    private static final String GET_BOOK_BY_ID_QUERY="SELECT * FROM LIBRARY WHERE ID =?";
    private static final String DELETE_BOOK_BY_ID_QUERY="DELETE FROM LIBRARY WHERE ID =?";

    private static final String GET_BOOK_QUERY="SELECT * FROM LIBRARY ";

    private static final String GET_QUERY_BY_BOOK_CATEGORY_AUTHOR="SELECT * FROM LIBRARY l WHERE l.category = :category";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String saveLibrary(Library library) {

        jdbcTemplate.update(INSERT_BOOK_QUERY, library.getId(),library.getIsbn(), library.getBook(), library.getDescription(), library.getCategory(), library.getAuthor(), library.getPublisher());
        return "library was  successfully added";
    }

    @Override
    public String updateLibrary(Library library) {
        jdbcTemplate.update(UPDATE_BOOK_BY_ID_QUERY, library.getId(),library.getBook());
        return "book was  successfully updated";


    }



    @Override
    public String deleteById(long id) {


        jdbcTemplate.update(DELETE_BOOK_BY_ID_QUERY, id);

        return "library with id no_ " + id + " was deleted successfully";
    }

    @Override
    public Library getById(long id) {

        return jdbcTemplate.queryForObject(GET_BOOK_BY_ID_QUERY, (rs, rowNum) -> {
            return new Library(rs.getInt("id"),rs.getString("isbn"), rs.getString("book"), rs.getString("description"), rs.getString("category"), rs.getString("author"), rs.getString("publihser"));
        }, id);
    }
    public List<Library> getBooksByCatAuthorBook(String category) {

        return jdbcTemplate.query( GET_QUERY_BY_BOOK_CATEGORY_AUTHOR, (rs, rowNum) -> {
            return new Library(rs.getInt("id"),rs.getString("isbn"), rs.getString("book"), rs.getString("description"), rs.getString("category"), rs.getString("author"), rs.getString("publihser"));
        }, category);
    }

    @Override
    public List<Library> allLibrary() {

        return jdbcTemplate.query(GET_BOOK_QUERY, (rs, rowNum) -> {
            return new Library (rs.getLong("id"), rs.getString("name"), rs.getString("isbn"), rs.getString("description"));
        });
    }


}


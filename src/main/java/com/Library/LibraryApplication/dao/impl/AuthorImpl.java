package com.Library.LibraryApplication.dao.impl;

import com.Library.LibraryApplication.dao.AuthorRepository;
import com.Library.LibraryApplication.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorImpl implements AuthorRepository {


    private static final String INSERT_AUTHOR_QUERY = "INSERT INTO AUTHORS(id, name, description) VALUES (?, ?, ?)";

    private static final String UPDATE_AUTHOR_BY_ID_QUERY ="UPDATE AUTHORS SET name=? WHERE ID=?";
    private static final String GET_AUTHOR_BY_ID_QUERY="SELECT * FROM AUTHORS WHERE ID =?";
    private static final String DELETE_AUTHOR_BY_ID_QUERY="DELETE FROM AUTHORS WHERE ID =?";
    private static final String GET_AUTHOR_QUERY="SELECT * FROM AUTHORS ";
    private static final String GET_AUTHOR_BY_NAME_QUERY="SELECT * FROM AUTHORS WHERE NAME =?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ResponseEntity<String> saveAuthor(Author author) {

        jdbcTemplate.update(INSERT_AUTHOR_QUERY,author.getId(),author.getName(), author.getDescription());
        return new ResponseEntity<>("author was  successfully added", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> updateAuthor(Author author) {
        jdbcTemplate.update(UPDATE_AUTHOR_BY_ID_QUERY,author.getId(),author.getName());
        return new ResponseEntity<>( "author was  successfully updated", HttpStatus.OK);


    }



    @Override
    public ResponseEntity<String> deleteById(long id) {


        jdbcTemplate.update(DELETE_AUTHOR_BY_ID_QUERY, id);

        return new  ResponseEntity<> ("Author with id no_ " + id + " was deleted successfully", HttpStatus.OK);
    }

    @Override
    public Author getById(long id) {

        Author author1 =  jdbcTemplate.queryForObject(GET_AUTHOR_BY_ID_QUERY, (rs, rowNum) -> {
            return new Author (rs.getLong("id"), rs.getString("name"), rs.getString("description"));
        }, id);
        return author1;
    }



    @Override
    public List<Author> allAuthors() {


        return jdbcTemplate.query(GET_AUTHOR_QUERY, (rs, rowNum) -> {
            return new Author (rs.getLong("id"), rs.getString("name"), rs.getString("description"));
        });
    }

	@Override
	public Author getByName(String name) {

        return jdbcTemplate.queryForObject(GET_AUTHOR_BY_NAME_QUERY, (rs, rowNum) -> {
            return new Author(rs.getLong("id"),rs.getString("name"), rs.getString("description"));
        }, name);
	}

}

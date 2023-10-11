package com.Library.LibraryApplication.dao.impl;

import com.Library.LibraryApplication.dao.PublisherRepository;
import com.Library.LibraryApplication.entity.Category;
import com.Library.LibraryApplication.entity.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class PublisherImpl implements PublisherRepository {

    private static final String INSERT_PUBLISHER_QUERY = "INSERT INTO PUBLISHERS(id,  name) VALUES ( ?, ?)";

    private static final String UPDATE_PUBLISHER_BY_ID_QUERY ="UPDATE PUBLISHERS SET name=? WHERE ID=?";
    private static final String GET_PUBLISHER_BY_ID_QUERY="SELECT * FROM PUBLISHERS WHERE ID =?";
    private static final String DELETE_PUBLISHER_BY_ID_QUERY="DELETE FROM PUBLISHERS WHERE ID =?";
    private static final String GET_PUBLISHER_QUERY="SELECT * FROM PUBLISHERS ";
    private static final String GET_PUBLISHER_BY_NAME_QUERY="SELECT * FROM PUBLISHERS WHERE NAME =?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String savePublisher(Publisher publisher) {

        jdbcTemplate.update(INSERT_PUBLISHER_QUERY, publisher.getId(), publisher.getName() );
        return "PUBLISHER was  successfully added";
    }

    @Override
    public String updatePublisher(Publisher publisher) {
        jdbcTemplate.update(UPDATE_PUBLISHER_BY_ID_QUERY,publisher.getId(),publisher.getName());
        return "PUBLISHER was  successfully updated";


    }



    @Override
    public String deleteById(long id) {


        jdbcTemplate.update(DELETE_PUBLISHER_BY_ID_QUERY, id);

        return "PUBLISHER with id no_ " + id + " was deleted successfully";
    }

    @Override
    public Publisher getById(long id) {

        return jdbcTemplate.queryForObject(GET_PUBLISHER_BY_ID_QUERY, (rs, rowNum) -> {
            return new Publisher(rs.getLong("id"),rs.getString("name"));
        }, id);
    }

    public List<Publisher> allPublishers() {

        return jdbcTemplate.query(GET_PUBLISHER_QUERY, (rs, rowNum) -> {
            return new Publisher (rs.getLong("id"), rs.getString("name"));
        });
    }

	@Override
	public Publisher getByName(String name) {
		return jdbcTemplate.queryForObject(GET_PUBLISHER_BY_NAME_QUERY, (rs, rowNum) -> {
            return new Publisher(rs.getLong("id"),rs.getString("name"));
        }, name);
	}



}

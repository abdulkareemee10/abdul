package com.Library.LibraryApplication.dao.impl;

import com.Library.LibraryApplication.dao.UserRepository;
import com.Library.LibraryApplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String INSERT_USER_QUERY = "INSERT INTO USER(id, name, surname, email) VALUES (?, ?, ?, ?)";

    private static final String UPDATE_USER_BY_ID_QUERY ="UPDATE USER SET name=? WHERE ID=?";
    private static final String GET_USER_BY_ID_QUERY="SELECT * FROM USER WHERE ID =?";
    private static final String DELETE_USER_BY_ID_QUERY="DELETE FROM USER WHERE ID =?";
    private static final String GET_USERS_QUERY="SELECT * FROM USER ";
    @Autowired
   private JdbcTemplate jdbcTemplate;

    @Override
    public String saveUser(User user) {

        jdbcTemplate.update(INSERT_USER_QUERY,user.getId(),user.getName(), user.getSurname(), user.getEmail());
        return "user was  successfully added";
    }

    @Override
    public String updateUser(User user) {
        jdbcTemplate.update(UPDATE_USER_BY_ID_QUERY,user.getId(),user.getName());
        return "user was  successfully updated";


    }



    @Override
    public String deleteById(long id) {


         jdbcTemplate.update(DELETE_USER_BY_ID_QUERY, id);

         return "user with id no_ " + id + " was deleted successfully";
    }

    @Override
    public User getById(long id) {

        return jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY, (rs, rowNum) -> {
            return new User(rs.getInt("id"),rs.getString("name"), rs.getString("surname"), rs.getString("email"));
        }, id);
    }

    @Override
    public List<User> allUser() {


        return jdbcTemplate.query(GET_USERS_QUERY, (rs, rowNum) -> {
            return new User (rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("email"));
        });
    }

}

package com.Library.LibraryApplication.dao.impl;

import com.Library.LibraryApplication.dao.CategoryRepository;
import com.Library.LibraryApplication.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryImpl implements CategoryRepository {

    private static final String INSERT_CATEGORY_QUERY = "INSERT INTO CATEGORIES(id,  name) VALUES ( ?, ?)";

    private static final String UPDATE_CATEGORY_BY_ID_QUERY ="UPDATE CATEGORIES SET name=? WHERE ID=?";
    private static final String GET_CATEGORY_BY_ID_QUERY="SELECT * FROM CATEGORIES WHERE ID =?";
    private static final String DELETE_CATEGORY_BY_ID_QUERY="DELETE FROM CATEGORIES WHERE ID =?";
    private static final String GET_CATEGORY_QUERY="SELECT * FROM CATEGORIES ";
    private static final String GET_CATEGORY_BY_NAME_QUERY="SELECT * FROM CATEGORIES WHERE NAME =?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String saveCategory(Category category) {

        jdbcTemplate.update(INSERT_CATEGORY_QUERY, category.getId(), category.getName());
        return "category was  successfully added";
    }

    @Override
    public String updateCategory(Category category) {
        jdbcTemplate.update(UPDATE_CATEGORY_BY_ID_QUERY,category.getId(),category.getName());
        return "category was  successfully updated";


    }



    @Override
    public String deleteById(long id) {


        jdbcTemplate.update(DELETE_CATEGORY_BY_ID_QUERY, id);

        return "Categories with id no_ " + id + " was deleted successfully";
    }

    @Override
    public Category getById(long id) {

        return jdbcTemplate.queryForObject(GET_CATEGORY_BY_ID_QUERY, (rs, rowNum) -> {
            return new Category(rs.getLong("id"),rs.getString("name"));
        }, id);
    }

    @Override
    public List<Category> allCategories() {


        return jdbcTemplate.query(GET_CATEGORY_QUERY, (rs, rowNum) -> {
            return new Category (rs.getLong("id"), rs.getString("name"));
        });
    }

	@Override
	public Category getByName(String name) {
		 return jdbcTemplate.queryForObject(GET_CATEGORY_BY_NAME_QUERY, (rs, rowNum) -> {
	            return new Category(rs.getLong("id"),rs.getString("name"));
	        }, name);
	}
	
}





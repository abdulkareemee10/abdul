package com.Library.LibraryApplication.dao;

import com.Library.LibraryApplication.entity.Category;

import java.util.List;

public interface CategoryRepository {
    String saveCategory(Category category);

    String updateCategory(Category category);

    String deleteById(long id);

    Category getById(long id);

    List<Category> allCategories();

	Category getByName(String name);
}

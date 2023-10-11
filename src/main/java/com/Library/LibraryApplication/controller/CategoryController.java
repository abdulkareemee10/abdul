package com.Library.LibraryApplication.controller;

import com.Library.LibraryApplication.dao.CategoryRepository;
import com.Library.LibraryApplication.dao.UserRepository;
import com.Library.LibraryApplication.entity.Category;
import com.Library.LibraryApplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/postcategory")
    public String saveUser(@RequestBody Category category) {

        return categoryRepository.saveCategory(category);

    }

    @PutMapping("/putcategory")
    public String updateUser(@RequestBody Category category) {

        return categoryRepository.updateCategory(category);

    }

    @GetMapping("/category/{id}")
    public Category getUser(@PathVariable("id") long id) {

        return categoryRepository.getById(id);

    }

    @GetMapping("/getcategory")
    public List<Category> getCategories() {

        return categoryRepository.allCategories();


    }
    @DeleteMapping("delcategory/{id}")
    public String deleteById( @PathVariable("id") long id) {

        return categoryRepository.deleteById(id);

    }

}



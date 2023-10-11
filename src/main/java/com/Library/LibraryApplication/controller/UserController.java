package com.Library.LibraryApplication.controller;

import com.Library.LibraryApplication.dao.UserRepository;
import com.Library.LibraryApplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    public String saveUser(@RequestBody User user) {

        return userRepository.saveUser(user);

    }

    @PutMapping("/user")
    public String updateUser(@RequestBody User user) {

        return userRepository.updateUser(user);

    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") long id) {

        return userRepository.getById(id);

    }

    @GetMapping("/users")
    public List<User> getUsers() {

        return userRepository.allUser();


    }
    @DeleteMapping("user/{id}")
    public String deleteById( @PathVariable("id") int id) {

        return userRepository.deleteById(id);

    }

}

package com.Library.LibraryApplication.dao;


import com.Library.LibraryApplication.entity.User;

import java.util.List;

public interface UserRepository {

    String saveUser(User user);

    String updateUser(User user);

   String deleteById(long id);

    User getById(long id);

    List<User> allUser();
}

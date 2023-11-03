package com.Library.LibraryApplication.controller;

import com.Library.LibraryApplication.dao.impl.LoginService;
import com.Library.LibraryApplication.entity.Login;
import com.Library.LibraryApplication.entity.Signup;
import com.Library.LibraryApplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user")
    public String Login(@RequestBody Login login) {

        return loginService.logins(login);

    }






}
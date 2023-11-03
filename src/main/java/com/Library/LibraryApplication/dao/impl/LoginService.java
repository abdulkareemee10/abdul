package com.Library.LibraryApplication.dao.impl;

import com.Library.LibraryApplication.dao.SignupRepository;
import com.Library.LibraryApplication.dao.UserRepository;
import com.Library.LibraryApplication.entity.Login;
import com.Library.LibraryApplication.entity.Signup;
import com.Library.LibraryApplication.entity.User;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    private SignupRepository signupRepository;

    private UserRepository userRepository;

   User user = new User();

    public String saveLogins(Signup signup){
        signupRepository.save(signup);
        return "sign up successfull";
    }

    public Optional<Signup> getById(Long id){
       Optional<Signup> signup1 =  signupRepository.findById(id);

       return signup1;
    }

    public List<Signup> getAll(){
        List<Signup> getSignup = signupRepository.findAll();
        return getSignup;
    }

    public String deleteById(Long id){

        signupRepository.deleteById(id);

        return "user deleted successfully";


        }

    public String logins(@NotNull Login login) {
    if (user.getEmail() == login.getUsername() && user.getPassword() == login.getPassword()) {
         return "login was successfull";

    }

        return "username or password is incorrect";

}
}

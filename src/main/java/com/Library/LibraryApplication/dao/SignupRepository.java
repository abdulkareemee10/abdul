package com.Library.LibraryApplication.dao;

import com.Library.LibraryApplication.entity.Signup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SignupRepository extends JpaRepository<Signup, Long> {

    Optional<Signup> findByEmail(String email);
}

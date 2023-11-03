package com.Library.LibraryApplication.dao;

import com.Library.LibraryApplication.entity.Signup;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignupRepository extends JpaRepository<Signup, Long> {

    Optional<Signup> findByEmail(String email);
}

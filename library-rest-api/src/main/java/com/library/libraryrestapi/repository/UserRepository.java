package com.library.libraryrestapi.repository;

import com.library.libraryrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByEmail(String email);

    User findByUsername(String username);




}

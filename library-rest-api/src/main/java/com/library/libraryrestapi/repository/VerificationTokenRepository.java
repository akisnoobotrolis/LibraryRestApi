package com.library.libraryrestapi.repository;


import com.library.libraryrestapi.entity.User;
import com.library.libraryrestapi.token.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);

    void deleteByUser(User user);
}
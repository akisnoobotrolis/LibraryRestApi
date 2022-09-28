package com.library.libraryrestapi.repository;

import com.library.libraryrestapi.entity.Book;
import com.library.libraryrestapi.entity.Lending;
import com.library.libraryrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LendingRepository extends JpaRepository<Lending,Long> {


    Lending findByBook(Book book);

    Lending findByBookId(Long bookId);

}
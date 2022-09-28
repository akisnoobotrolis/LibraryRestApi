package com.library.libraryrestapi.repository;

import com.library.libraryrestapi.entity.Book;
import com.library.libraryrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {


    Book findByTitle(String title);



}

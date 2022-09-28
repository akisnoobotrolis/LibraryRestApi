package com.library.libraryrestapi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor /* Constructor with all arguments*/
@NoArgsConstructor /* Constructor with no arguments*/
@Builder
@Table( uniqueConstraints={@UniqueConstraint(
        name="title_unique",
        columnNames = "title")})
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence"
            ,allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence")
    private Long id;
   private String title;
   private String author;
   private int numberOfPages;
   private String publisher;
   private boolean isLent=false;


}

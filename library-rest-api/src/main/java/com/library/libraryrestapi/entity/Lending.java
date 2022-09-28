package com.library.libraryrestapi.entity;


import lombok.*;


import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor /* Constructor with all arguments*/
@NoArgsConstructor /* Constructor with no arguments*/
@Builder
public class Lending {
    @Id
    @SequenceGenerator(
            name = "Lending_sequence",
            sequenceName = "lending_sequence"
            ,allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lending_sequence")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "librarian_id",referencedColumnName = "id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id",referencedColumnName = "id")
    private Book book;





}
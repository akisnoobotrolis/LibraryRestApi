package com.library.libraryrestapi.dto;


import lombok.*;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {




    private String title;
    private String author;
    private int numberOfPages;
    private String publisher;

}

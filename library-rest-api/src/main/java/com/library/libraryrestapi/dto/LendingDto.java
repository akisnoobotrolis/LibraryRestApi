package com.library.libraryrestapi.dto;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class LendingDto{


        @NotNull
        private Long userId;
        @NotNull
        private String book;



}

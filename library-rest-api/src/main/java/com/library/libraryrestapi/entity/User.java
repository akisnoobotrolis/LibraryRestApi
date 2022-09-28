package com.library.libraryrestapi.entity;



import lombok.*;

import javax.persistence.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(
        uniqueConstraints={@UniqueConstraint(
                name="email_unique",
                columnNames = "email"),@UniqueConstraint(
                name="username_unique",
                columnNames = "username")}

)
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence"
            ,allocationSize = 1

    )
    @GeneratedValue(
            strategy =GenerationType.SEQUENCE,
            generator = "user_sequence")
    private Long id;

    private String username;

    private String firstName;


    private String lastName;

    private String email;

    private String password;





    private boolean enabled=false;


}
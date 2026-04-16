<<<<<<< HEAD
package com.example.letsgo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;
    private String role;
}
=======
package backend.Hotel_booking_System.model;

public class User {
}
>>>>>>> a59c5c2e929b6fae6b65d3404aa019396b886495

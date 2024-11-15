package com.kjw.ecommerce.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Long idx;

    @Column(name = "id", nullable = false, length = 30)
    private String id;

    @Column(name = "password", nullable = false, length = 25)
    private String password;

    @Column(name = "phonenumber", length = 11)
    private String phonenumber;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "address", length = 60)
    private String address;

}
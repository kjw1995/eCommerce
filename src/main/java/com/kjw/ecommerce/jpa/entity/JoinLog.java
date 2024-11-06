package com.kjw.ecommerce.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "join_log")
public class JoinLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Long id;

    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "createdAt")
    private Instant createdAt;

}
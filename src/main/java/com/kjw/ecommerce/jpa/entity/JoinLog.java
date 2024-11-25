package com.kjw.ecommerce.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "join_log")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Long id;

    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

}
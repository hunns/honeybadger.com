package com.honeybadger.demowebapp.security;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Token_Seq")
    @Column(name = "Token_id")
    private int id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Date issuedAt;

    @Column(nullable = false)
    private Date expiredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser  appUser;

}

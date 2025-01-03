package com.honeybadger.demowebapp.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {
    Optional<Token> findByToken(String jwt);

    @Query(value = """
      select t from Token t inner join AppUser u\s
      on t.appUser.id = u.id\s
      where u.email=:username\s
      and t.expiredAt >= :currentDate\s
      and t.token = :token\s
      """)
    Optional<Token> findTokenByUser(String username, String token, Date currentDate);
}

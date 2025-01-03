package com.honeybadger.demowebapp.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Service
public class JWTService {
    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${application.security.jwt.expiration}")
    private long EXPIRATION_DURATION;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long REFRESH_EXPIRATION_DURATION;


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails

    ) {
        return Jwts
                .builder()
                    .claims()
                    .subject((userDetails.getUsername()))
                    .issuedAt(Date.from(Instant.now()))
                    .expiration(Date.from(Instant.now().plusMillis(EXPIRATION_DURATION)))
                    .add(extraClaims)
                .and()
                .signWith(getSignInKey())
                .compact();

    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                //.parserBuilder()
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public Date extractIssueAt(String token) {
        return extractClaim(token,Claims::getIssuedAt);
    }

    public Date extractExpiredAt(String token) {
        return extractClaim(token,Claims::getExpiration);
    }
}

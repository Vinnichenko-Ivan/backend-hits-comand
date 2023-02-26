package ru.hits.hitsback.timetable.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hits.hitsback.timetable.model.entity.Account;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class JwtService {

    private final Long expiration = 100000l;

    private final String secret = "secrewqewqadssadsdasdasdaadsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdsdseweqweqweqewqwet";

    private SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());

    public Claims getAllClaimsFromToken(String token) {
        return null;
//        return jwtParser.parseClaimsJws(token).getBody();
    }

    public String generateToken(Account user) {
        return doGenerateToken(
                new HashMap<String, Object>() {{ put("role", user.getRoles()); }},
                user.getId(),
                expiration
        );
    }

    private String doGenerateToken(Map<String, Object> claims, UUID userId, long expiration) {
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expiration * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId.toString())
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

    private Account getAccountByToken(String token){

        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        String userId = claims.getSubject();
        String role = (String) claims.get("role");

        System.out.println("User Id: " + userId);
        System.out.println("Role: " + role);

        return null;
    }
}

package edu.miu.cs544.moe.emr.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtServiceImpl implements JwtService {
    @Value("${app.security.jwt.secret}")
    private String secret;
    @Value("${app.security.jwt.expiration}")
    private long accessTokenExpirationTime;
    @Value("${app.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpirationTime;

    @Override
    public String generateAccessToken(UserDetails user) {
        String authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        Map<String, String> claims = Map.of(
                "authorities", authorities,
                "type", "access-token"
        );
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpirationTime))
                .claims(claims)
                .signWith(getSignInKey())
                .compact();
    }

    @Override
    public String generateRefreshToken(UserDetails user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpirationTime))
                .claim("type", "refresh-token")
                .signWith(getSignInKey())
                .compact();
    }

    @Override
    public String getEmail(String accessToken) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(accessToken)
                .getPayload()
                .getSubject();
    }

    @Override
    public boolean validateAccessToken(String accessToken) {
        try {
            String type = Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload().get("type", String.class);
            return "access-token".equals(type);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean validateRefreshToken(String refreshToken) {
        try {
            String type = Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(refreshToken)
                    .getPayload().get("type", String.class);
            return "refresh-token".equals(type);
        } catch (Exception e) {
            return false;
        }
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8));
    }
}

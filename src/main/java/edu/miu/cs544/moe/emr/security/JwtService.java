package edu.miu.cs544.moe.emr.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateAccessToken(UserDetails user);

    String generateRefreshToken(UserDetails user);

    String getUsername(String accessToken);

    UserDetails getUserDetails(String accessToken);

    boolean validateAccessToken(String accessToken);

    boolean validateRefreshToken(String refreshToken);
}

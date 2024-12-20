package edu.miu.cs544.moe.emr.domain.auth;

import edu.miu.cs544.moe.emr.domain.auth.dto.*;
import edu.miu.cs544.moe.emr.domain.user.Role;
import edu.miu.cs544.moe.emr.domain.user.User;
import edu.miu.cs544.moe.emr.domain.user.UserRepository;
import edu.miu.cs544.moe.emr.domain.user.dto.UpdateUserRequest;
import edu.miu.cs544.moe.emr.domain.user.dto.UserResponse;
import edu.miu.cs544.moe.emr.exception.UnauthorizedException;
import edu.miu.cs544.moe.emr.helper.LocaleMessageProvider;
import edu.miu.cs544.moe.emr.helper.Mapper;
import edu.miu.cs544.moe.emr.security.JwtService;
import edu.miu.cs544.moe.emr.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService tokenService;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final LocaleMessageProvider messageProvider;

    @Override
    public UserResponse register(RegisterUser registerUser) {
        User user = this.mapper.map(registerUser, User.class);
        user.setRole(Role.ROLE_USER);
        user.setPassword(this.passwordEncoder.encode(registerUser.getPassword()));
        return this.mapper.map(this.userRepository.save(user), UserResponse.class);
    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String accessToken = this.tokenService.generateAccessToken(userDetails);
            String refreshToken = this.tokenService.generateRefreshToken(userDetails);
            return new TokenResponse(accessToken, refreshToken);
        }
        throw new UnauthorizedException(this.messageProvider.getMessage("auth.exceptions.invalidCredentials", null));
    }

    @Override
    public TokenResponse refreshToken(RefreshToken refreshToken) {
        if (this.tokenService.validateRefreshToken(refreshToken.token())) {
            String username = this.tokenService.getUsername(refreshToken.token());
            User user = this.userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new UnauthorizedException(this.messageProvider.getMessage("user.exceptions.notFound", null)));
            UserDetails userDetails = this.mapper.map(user);
            String accessToken = this.tokenService.generateAccessToken(userDetails);
            String newRefreshToken = this.tokenService.generateRefreshToken(userDetails);
            return new TokenResponse(accessToken, newRefreshToken);
        }
        throw new UnauthorizedException(this.messageProvider.getMessage("auth.exceptions.invalidToken", null));
    }

    @Override
    public UserResponse getProfile() {
        String username = SecurityUtils.getPrinciple();
        User user = this.userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new UnauthorizedException(this.messageProvider.getMessage("user.exceptions.notFound", null)));
        return this.mapper.map(user, UserResponse.class);
    }

    @Override
    @Transactional
    public UserResponse updateProfile(UpdateUserRequest updateUserRequest) {
        String username = SecurityUtils.getPrinciple();
        User user = this.userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new UnauthorizedException(this.messageProvider.getMessage("user.exceptions.notFound", null)));
        user.setName(updateUserRequest.getName());
        this.userRepository.save(user);
        return this.mapper.map(user, UserResponse.class);
    }

    @Override
    public void updatePassword(UpdatePassword updatePassword) {
        String username = SecurityUtils.getPrinciple();
        User user = this.userRepository.findByUsernameIgnoreCase(username).orElseThrow(() -> new UnauthorizedException(this.messageProvider.getMessage("user.exceptions.notFound", null)));
        if (!this.passwordEncoder.matches(updatePassword.oldPassword(), user.getPassword())) {
            throw new UnauthorizedException(this.messageProvider.getMessage("auth.exceptions.invalidCredentials", null));
        }
        user.setPassword(this.passwordEncoder.encode(updatePassword.newPassword()));
        this.userRepository.save(user);
    }
}

package edu.miu.cs544.moe.emr.domain.auth;

import edu.miu.cs544.moe.emr.domain.auth.dto.*;
import edu.miu.cs544.moe.emr.domain.user.dto.UpdateUserRequest;
import edu.miu.cs544.moe.emr.domain.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthService authService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody @Validated RegisterUser user) {
        return this.authService.register(user);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Validated LoginRequest user) {
        return this.authService.login(user);
    }

    @PostMapping("/refresh")
    public TokenResponse refreshToken(@RequestBody @Validated RefreshToken token) {
        return this.authService.refreshToken(token);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public UserResponse getProfile() {
        return this.authService.getProfile();
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/profile")
    public UserResponse updateProfile(@RequestBody @Validated UpdateUserRequest user) {
        return this.authService.updateProfile(user);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/password")
    public void updatePassword(@RequestBody @Validated UpdatePassword updatePassword) {
        this.authService.updatePassword(updatePassword);
    }
}

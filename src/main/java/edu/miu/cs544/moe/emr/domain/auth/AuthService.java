package edu.miu.cs544.moe.emr.domain.auth;

import edu.miu.cs544.moe.emr.domain.auth.dto.*;
import edu.miu.cs544.moe.emr.domain.user.dto.UpdateUser;
import edu.miu.cs544.moe.emr.domain.user.dto.UserResponse;

public interface AuthService {
    UserResponse register(RegisterUser registerUser);
    TokenResponse login(LoginRequest loginRequest);
    TokenResponse refreshToken(RefreshToken refreshToken);
    UserResponse getProfile();
    UserResponse updateProfile(UpdateUser updateUser);
    void updatePassword(UpdatePassword updatePassword);
}

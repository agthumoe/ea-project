package edu.miu.cs544.moe.emr.application;

import edu.miu.cs544.moe.emr.domain.user.UserService;
import edu.miu.cs544.moe.emr.domain.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
@Component
public class UserEditor extends PropertyEditorSupport {
    private final UserService userService;
    @Autowired
    public UserEditor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        UserResponse user = userService.getOneUser(Long.parseLong(text)).orElseThrow(() -> new IllegalArgumentException("User not found"));
        setValue(user);
    }
}

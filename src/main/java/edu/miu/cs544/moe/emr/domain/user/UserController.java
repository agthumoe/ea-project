package edu.miu.cs544.moe.emr.domain.user;

import edu.miu.cs544.moe.emr.application.UserEditor;
import edu.miu.cs544.moe.emr.domain.user.dto.CreateUser;
import edu.miu.cs544.moe.emr.domain.user.dto.UpdateUser;
import edu.miu.cs544.moe.emr.domain.user.dto.UserResponse;
import jakarta.jms.MapMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserEditor userEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(UserResponse.class, userEditor);
    }

    @PostMapping
    public UserResponse create(@RequestBody @Validated CreateUser user) {
        return this.userService.create(user);
    }

    @GetMapping()
    public List<UserResponse> getAllUsers(@RequestHeader(value = "Accept-Language", required = false, defaultValue = "US") Locale locale) {
        var result = this.userService.getAllUsers();
        this.jmsTemplate.convertAndSend("usersQueue", result);
        return result;
    }

    @JmsListener(destination = "usersQueue")
    public void processMessage(List<UserResponse> content) {
        System.out.println("Received: " + content);
    }

    @GetMapping("{id}")
    public UserResponse getOne(@PathVariable("id") UserResponse user) {
        return user;
    }

    @PutMapping("{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody @Validated UpdateUser user) {
        return this.userService.update(id, user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.userService.deleteById(id);
    }
}

package ua.vladyslav.jdbc_spring.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.RequiredArgsConstructor;
import ua.vladyslav.jdbc_spring.model.User;
import ua.vladyslav.jdbc_spring.service.impl.UserService;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator{
    private final UserService userService;


    @SuppressWarnings("null")
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @SuppressWarnings("null")
    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;
        
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "This email is already used");
        }

        if (userService.findByUsername(user.getUsername()).isPresent()) {
            errors.rejectValue("username", "", "This username is already used");
        }
    }

}

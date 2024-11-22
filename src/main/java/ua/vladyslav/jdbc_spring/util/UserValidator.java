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


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "", "This email is already used");
        }
    }

}

package com.example.testfordima.valid;

import com.example.testfordima.model.User;
import com.example.testfordima.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class UserValidator implements Validator {
    private final UserDetailsService userDetailsService;

    public UserValidator(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if(userDetailsService.loadUserByUsername(user.getUsername()) != null){
            errors.reject("", "User is already exist");
        }
    }
}

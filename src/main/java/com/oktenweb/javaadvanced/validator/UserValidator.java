package com.oktenweb.javaadvanced.validator;

import com.oktenweb.javaadvanced.entity.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (user.getName() != null && user.getName().trim().length() > 0) {
            if (user.getName().charAt(0) < 65 || user.getName().charAt(0) > 90) {
                errors.rejectValue("name", "user.name.capital-letter",
                        "Name must start with capital letter");
            }
        }
    }
}

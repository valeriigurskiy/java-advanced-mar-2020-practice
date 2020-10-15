package com.oktenweb.javaadvanced.validator;

import com.oktenweb.javaadvanced.dao.UserDao;
import com.oktenweb.javaadvanced.dto.UserDTO;
import com.oktenweb.javaadvanced.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    private UserDao userDao;

    @Autowired
    public UniqueUserNameValidator(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public void initialize(UniqueUserName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        if(name != null && name.length() != 0){
            final User user = userDao.findByTitle(name);
            return user == null;
        }
        return false;
    }
}

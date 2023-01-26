package com.online.diary.validation;


import com.online.diary.exception.ValidationException;

public class UserPasswordValidation {

    public static void passwordValidate(String password) throws ValidationException {
        if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$")){
            throw new ValidationException("Password is not valid!");
        }

    }

}

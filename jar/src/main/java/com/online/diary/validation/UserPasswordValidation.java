package com.online.diary.validation;


import com.online.diary.exception.ValidationException;

public class UserPasswordValidation {

    public static void passwordValidate(String password) throws ValidationException {
        if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$")){
            throw new ValidationException("The password must be between 8 and 20 characters long, with at least one uppercase letter, one lowercase letter, and a number.");
        }

    }

}

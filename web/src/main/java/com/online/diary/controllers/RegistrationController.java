package com.online.diary.controllers;

import com.online.diary.exception.ValidationException;
import com.online.diary.fasads.UserFacade;
import com.online.diary.forms.UserForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    @Autowired
    UserFacade userFacade;

    @GetMapping
    public ModelAndView preRegistration(){
        return new ModelAndView("registration")
                .addObject("userRegistrationForm", new UserForm());
    }

    @SneakyThrows
    @PostMapping
    public ModelAndView postRegistration(@ModelAttribute("userRegistrationForm") UserForm userRegistrationForm, HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = null;
        if (userRegistrationForm.getConfirmPassword().equals(userRegistrationForm.getPassword())) {
            try {
                userFacade.addUser(userRegistrationForm);
                response.sendRedirect(request.getContextPath());
            } catch (ValidationException e) {
                return new ModelAndView("registration")
                        .addObject("errorMessage", e)
                        .addObject("userRegistrationForm", new UserForm());
            }
        }else{
            modelAndView = new ModelAndView("registration")
                    .addObject("errorMessage","Passwords do not match, please try again");
        }
        return modelAndView;
    }
}

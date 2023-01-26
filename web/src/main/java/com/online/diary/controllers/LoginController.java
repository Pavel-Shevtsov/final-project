package com.online.diary.controllers;

import com.online.diary.fasads.MomentsOfLifeFacade;
import com.online.diary.fasads.UserFacade;
import com.online.diary.forms.UserForm;
import com.online.diary.model.MomentsOfLife;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "/loginS")
public class LoginController {

    @Autowired
    UserFacade userFacade;
    @Autowired
    MomentsOfLifeFacade momentsOfLifeFacade;

    @PostMapping
    public void postLogin(@ModelAttribute("loginForm")UserForm loginForm, HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView;
        HttpSession session = request.getSession(true);
        UserForm userByUsername = userFacade.getUserByUsername(loginForm.getUsername());
        session.setAttribute("user",userByUsername);
        session.setAttribute("visitCounter",1);
    }
}

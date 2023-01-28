package com.online.diary.controllers;

import com.online.diary.fasads.PostFacade;
import com.online.diary.fasads.UserFacade;
import com.online.diary.forms.UserForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    UserFacade userFacade;


    @GetMapping(value = {"/login"})
    public void preLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/login");
    }

    @PostMapping(value = {"/loginS"})
    public void postLogin(@ModelAttribute("loginForm")UserForm loginForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView modelAndView;
        HttpSession session = request.getSession(true);
        UserForm userByUsername = userFacade.getUserByUsername(loginForm.getUsername());
        session.setAttribute("user",userByUsername);
        session.setAttribute("visitCounter",1);
        response.sendRedirect(request.getContextPath()+"/welcome");
    }
}

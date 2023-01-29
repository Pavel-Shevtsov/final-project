package com.online.diary.controllers;

import com.online.diary.fasads.PostFacade;
import com.online.diary.forms.UserForm;
import com.online.diary.model.Post;
import com.online.diary.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/welcome")
public class WelcomeController {
    @Autowired
    PostFacade postFacade;
    @GetMapping
    public ModelAndView preWelcome(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("welcome");
        HttpSession session = request.getSession();
        UserForm user = (UserForm) session.getAttribute("user");
        int visitCounter = (int) session.getAttribute("visitCounter");
        if (user.getRole().equalsIgnoreCase("Admin")){
            List<Post> notApproved = postFacade.getNotApproved();
            modelAndView.addObject("notApproved",notApproved);
        }else{
                modelAndView .addObject("userPost",postFacade.getAllPublicPosts());
            }
        return modelAndView;
    }
}

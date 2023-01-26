package com.online.diary.controllers;

import com.online.diary.fasads.MomentsOfLifeFacade;
import com.online.diary.model.MomentsOfLife;
import com.online.diary.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "/welcome")
public class WelcomeController {
    @Autowired
    MomentsOfLifeFacade momentsOfLifeFacade;
    @GetMapping
    public ModelAndView preWelcome(HttpServletRequest request){
        ModelAndView modelAndView;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int visitCounter = (int) session.getAttribute("visitCounter");
        if (user.getRole().equalsIgnoreCase("Admin")){
            List<MomentsOfLife> notApproved = momentsOfLifeFacade.getNotApproved(false, null);
            modelAndView = new ModelAndView("welcome")
                    .addObject("notApproved",notApproved);
        }else{
                modelAndView = new ModelAndView("welcome")
                        .addObject("userMomentOfLife",user.getMomentsOfLives());
            }

        return modelAndView;
    }
}

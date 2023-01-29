package com.online.diary.controllers.user;

import com.online.diary.exception.ValidationException;
import com.online.diary.fasads.UserFacade;
import com.online.diary.forms.UserForm;
import com.online.diary.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserFacade userFacade;

    @GetMapping(value = {"/users"})
    public ModelAndView preUsers(HttpServletRequest request){
        User adminUser = (User) request.getSession().getAttribute("user");
        List<User> users = userFacade.users();
        List<User> otherUsers = new ArrayList<>();
        if (users.size()!=1)
        for (User user:users){
            if (!user.getUsername().equals(adminUser.getUsername())){
                otherUsers.add(user);
            }
        }
        return new ModelAndView("users")
                .addObject("otherUsers", otherUsers);
    }

    @SneakyThrows
    @PostMapping(value = {"/update"})
    public ModelAndView postUpdate(@ModelAttribute("userForm") UserForm updatedUser,HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = null;
        try {
            userFacade.update(updatedUser);
            response.sendRedirect(request.getContextPath());
        } catch (ValidationException message) {
             modelAndView = new ModelAndView("update")
                    .addObject("errorMessage",message)
                    .addObject("userUpdateForm",updatedUser);
        }
      return modelAndView;
    }

    @GetMapping(value = {"/delete"})
    public void preDelete(@RequestParam("id") long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserForm user = (UserForm) request.getSession().getAttribute("user");
        userFacade.delete(id);
        if (user.getRole().equalsIgnoreCase("Admin")) {
            response.sendRedirect(request.getContextPath() + "/user/users");
        }else {
            response.sendRedirect(request.getContextPath());
        }
    }

    @GetMapping(value = {"/page"})
    public ModelAndView prePage(@ModelAttribute("id") long userId,HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("userPage");
        UserForm userById = userFacade.getById(userId);
            UserForm user = (UserForm) request.getSession().getAttribute("user");
            if (user != null) {
                if (user.getUsername().equalsIgnoreCase(userById.getUsername())) {
                    modelAndView.addObject("userForm", userById)
                            .addObject("myPosts", userById.getPosts())
                            .addObject("access","full");
                } else {
                    modelAndView .addObject("userForm", userById)
                            .addObject("access","limited");
                }
            }else{
                modelAndView.addObject("userForm",userById)
                        .addObject("access","limited");;
            }
        return modelAndView;
    }
    @GetMapping(value = {"/logout"})
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }
}

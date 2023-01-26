package com.online.diary.controllers.user;

import com.online.diary.fasads.UserFacade;
import com.online.diary.forms.UserForm;
import com.online.diary.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping(value = {"/update"})
    public ModelAndView preUpdate(){

        return null;
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
}

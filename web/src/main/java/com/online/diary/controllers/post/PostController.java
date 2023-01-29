package com.online.diary.controllers.post;

import com.online.diary.fasads.PostFacade;
import com.online.diary.fasads.UserFacade;
import com.online.diary.forms.PostForm;
import com.online.diary.forms.UserForm;
import com.online.diary.model.Post;
import com.online.diary.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    PostFacade postFacade;
    @Autowired
    UserFacade userFacade;

    @GetMapping(value = {"/create"})
    public ModelAndView preCreate(){
        return new ModelAndView("createPost")
                .addObject("postForm",new PostForm());
    }

    @SneakyThrows
    @PostMapping(value = {"/create"})
    public void postCreate(@ModelAttribute("postForm") PostForm postForm, HttpServletRequest request, HttpServletResponse response){
        postForm.setPublicationDate(LocalDate.now());
        UserForm userForm = (UserForm) request.getSession().getAttribute("user");
        List<PostForm> posts = userForm.getPosts();
        posts.add(postForm);
        userFacade.update(userForm);
        postForm.setUser(userFacade.buildUser(userForm));
        postFacade.add(postForm);
        response.sendRedirect(request.getContextPath()+ "/welcome");
    }

    @SneakyThrows
    @GetMapping(value = {"/delete"})
    public void preDelete(@ModelAttribute("id")Long postId,HttpServletRequest request, HttpServletResponse response){
        PostForm postById = postFacade.getById(postId);
        UserForm user = (UserForm) request.getSession().getAttribute("user");
        List<PostForm> posts = user.getPosts();
        posts.removeIf(postForm -> postForm.getId().equals(postById.getId()));

        user.setPosts(posts);
        userFacade.update(user);

        postFacade.delete(postId);
        response.sendRedirect(request.getContextPath() + "/welcome");
    }

    @GetMapping(value = {"/update"})
    public ModelAndView preUpdate(@ModelAttribute("id") long postId){
        PostForm byId = postFacade.getById(postId);
        return new ModelAndView("updatePost")
                .addObject("postForm", byId);
    }

    @SneakyThrows
    @PostMapping(value = {"/update"})
    public void postUpdate(@ModelAttribute("postForm") PostForm postFormUpdate, HttpServletResponse response, HttpServletRequest request){
        User user = postFacade.getById(postFormUpdate.getId()).getUser();
        postFormUpdate.setUser(user);
        postFacade.update(postFormUpdate);
        response.sendRedirect(request.getContextPath() +"/welcome");
    }
    @GetMapping(value = {"/read"})
    public ModelAndView preRead(@ModelAttribute("id") long postId){
        PostForm byId = postFacade.getById(postId);
        User user = byId.getUser();
        return  new ModelAndView("readPost")
                .addObject("postForm", byId)
                .addObject("author", user);
    }
    @GetMapping(value = {"/public"})
    public ModelAndView prePublic(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("listOfApprovedPosts");
        List<Post> posts = postFacade.allApproved();
        if (posts.size()==0){
            modelAndView.addObject("message","Public posts coming soon");
        }else{
            modelAndView.addObject("publicPosts",posts);
        }
        return modelAndView;
    }
    @GetMapping(value = {"/free/read"})
    public ModelAndView preFreeRead(@ModelAttribute("id") long postId){
        PostForm byId = postFacade.getById(postId);
        return  new ModelAndView("readApprovedPosts")
                .addObject("postForm", byId)
                .addObject("author", byId.getUser());
    }

    @SneakyThrows
    @PostMapping(value = {"/approved"})
    public void approvedPost(@ModelAttribute("postForm") PostForm postForm, HttpServletRequest request ,HttpServletResponse response){
        User user = postFacade.getById(postForm.getId()).getUser();
        postForm.setUser(user);
        postFacade.update(postForm);
        response.sendRedirect(request.getContextPath() + "/welcome");

    }
}

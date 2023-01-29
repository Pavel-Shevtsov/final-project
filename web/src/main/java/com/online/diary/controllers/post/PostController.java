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
import java.util.ArrayList;
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
    public ModelAndView preRead(@ModelAttribute("id") long postId, HttpServletRequest request){
       ModelAndView modelAndView = new ModelAndView("readPost");
        PostForm byId = postFacade.getById(postId);
        UserForm userForm = (UserForm) request.getSession().getAttribute("user");
        User user = byId.getUser();
        if (userForm==null){
            modelAndView.addObject("access","limited");
        }else{
            modelAndView.addObject("access","preLimited");
        }
        modelAndView.addObject("postForm", byId)
                .addObject("author", user);
        return modelAndView;
    }
    @GetMapping(value = {"/public"})
    public ModelAndView prePublic(HttpServletRequest request,HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView("listOfApprovedPosts");
        List<Post> adminPost = new ArrayList<>();
        List<Post> posts = postFacade.getAllEverythingIsApproved();
        for(Post post:posts){
            if (post.getUser().getRole().equalsIgnoreCase("admin")){
                adminPost.add(post);
            }
        }
        if (adminPost.size()==0){
            modelAndView.addObject("message","Public posts coming soon");
        }else{
            modelAndView.addObject("publicPosts",adminPost);
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
    @PostMapping(value = {"/search"})
    public ModelAndView searchPost(@ModelAttribute("searchParameter") String searchParameter){
        ModelAndView modelAndView = new ModelAndView("welcome");
        List<Post> foundPost = new ArrayList<>();
        List<Post> posts = postFacade.getAllPublicPosts();
        for (Post post:posts){
            if (post.getTag().equalsIgnoreCase(searchParameter)){
                foundPost.add(post);
            }else{
                if (post.getUser().getUsername().equalsIgnoreCase(searchParameter)){
                    foundPost.add(post);
                }
            }
        }
        if (foundPost.size()!=0){
            modelAndView.addObject("userPost",foundPost);
        }else{
            modelAndView.addObject("notFound","no matching results");
        }
        return modelAndView;
    }
}

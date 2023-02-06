package com.online.diary.fasads;

import com.online.diary.exception.ValidationException;
import com.online.diary.forms.PostForm;
import com.online.diary.forms.UserForm;
import com.online.diary.entity.Post;
import com.online.diary.entity.User;
import com.online.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.online.diary.validation.UserPasswordValidation.passwordValidate;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserFacade {

    @Autowired
    UserService userService;

    public User buildUser(UserForm userForm){
        User user = new User();
        user.setId(userForm.getId());
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setRole(userForm.getRole());
        user.setEmail(userForm.getEmail());
        user.setDateOfBirth(userForm.getDateOfBirth());
        user.setImage(userForm.getImage());
        List<Post> posts = new ArrayList<>();

        if (userForm.getPosts()!=null) {
            for (PostForm postForm : userForm.getPosts()) {
                Post post = new Post();
                post.setId(postForm.getId());
                post.setTag(postForm.getTag());
                post.setText(postForm.getText());
                post.setPublicationDate(postForm.getPublicationDate());
                post.setLastUpdateDate(postForm.getLastUpdateDate());
                post.setIsApprovedForPublication(postForm.getIsApprovedForPublication());
                post.setIsPrivate(postForm.getIsPrivate());
                posts.add(post);
            }
        }
        return user;
    }

    public UserForm getUserByUsername(String username){
        User byUsername = userService.getByUsername(username);
        if (byUsername == null){
            return null;
        }
        return new UserForm(byUsername);
    }

    public boolean getUserByEmail(String email){
        User byEmail = userService.getByEmail(email);
        if(byEmail==null){
            return false;
        }
        return true;
    }

    public UserForm getById(long id){
        return new UserForm(userService.getById(id));
    }

    public void addUser (UserForm userForm) throws ValidationException {
        try {
            passwordValidate(userForm.getPassword());
        } catch (ValidationException e) {
            throw new ValidationException(e);
        }

        if (userService.getByUsername(userForm.getUsername())==null){
            if (userService.getByEmail(userForm.getEmail())==null){
                userForm.setRole("User");
                User user = buildUser(userForm);
                userService.add(user);
            }else{
                throw new ValidationException("A user with this email is already registered");
            }
        }else{
            throw new ValidationException("A user with this name is already registered");
        }
    }

    public List<User> users (){
        return userService.getAll();
    }

    public void update(UserForm userForm) throws ValidationException {
        if (userForm.getNewUsername()!=null&&!userForm.getNewUsername().equals("")){
            if (!userForm.getNewUsername().equalsIgnoreCase(userForm.getUsername())) {
                if (userService.getByUsername(userForm.getNewUsername()) == null) {
                    userForm.setUsername(userForm.getNewUsername());
                } else {
                    throw new ValidationException("A user with this name is already registered");
                }
            }
        }
        if (userForm.getNewEmail()!=null&&!userForm.getNewEmail().equals("")){
            if (!userForm.getNewEmail().equalsIgnoreCase(userForm.getEmail())){
                if (userService.getByEmail(userForm.getNewEmail()) == null) {
                    userForm.setEmail(userForm.getNewEmail());
                } else {
                    throw new ValidationException("A user with this email is already registered");
                }
            }
        }
        if (userForm.getNewDateOfBirth()!=null){
            userForm.setDateOfBirth(userForm.getNewDateOfBirth());
        }
        if (userForm.getNewPassword()!=null){
            if (!userForm.getNewPassword().equals("")) {
                passwordValidate(userForm.getNewPassword());
                userForm.setPassword(userForm.getNewPassword());
            }
        }

        userService.modify(buildUser(userForm));
    }

    public void delete(long id){
        userService.delete(id);
    }
}

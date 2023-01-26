package com.online.diary.fasads;

import com.online.diary.exception.ValidationException;
import com.online.diary.forms.MomentsOfLifeForm;
import com.online.diary.forms.UserForm;
import com.online.diary.model.MomentsOfLife;
import com.online.diary.model.User;
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
        user.setEMail(userForm.getEMail());
        user.setBirthday(userForm.getBirthday());
        user.setImage(userForm.getImage());
        List<MomentsOfLife> moments = new ArrayList<>();

        for (MomentsOfLifeForm momentsOfLifeForm : userForm.getMomentsOfLives()) {
            MomentsOfLife momentsOfLife = new MomentsOfLife();
            momentsOfLife.setId(momentsOfLifeForm.getId());
            momentsOfLife.setTag(momentsOfLifeForm.getTag());
            momentsOfLife.setText(momentsOfLifeForm.getText());
            momentsOfLife.setPublicationDate(momentsOfLifeForm.getPublicationDate());
            momentsOfLife.setIsApprovedForPublication(momentsOfLifeForm.getIsApprovedForPublication());
            momentsOfLife.setIsPrivate(momentsOfLifeForm.getIsPrivate());
            moments.add(momentsOfLife);
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

    public UserForm getBuId(long id){
        return new UserForm(userService.getById(id));
    }

    public void addUser (UserForm userForm) throws ValidationException {
        try {
            passwordValidate(userForm.getPassword());
        } catch (ValidationException e) {
            throw new ValidationException(e);
        }

        if (userService.getByUsername(userForm.getNewUsername())==null){
            if (userService.getByEmail(userForm.getNewEmail())==null){
                User user = buildUser(userForm);
                user.setRole("User");
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
        if (userForm.getNewUsername()!=null){
            if (userService.getByUsername(userForm.getNewUsername())==null){
                userForm.setUsername(userForm.getUsername());
            }else{
                throw new ValidationException("A user with this name is already registered");
            }
        }
        if (userForm.getNewEmail()!=null){
           if (userService.getByEmail(userForm.getNewEmail())==null){
               userForm.setEMail(userForm.getEMail());
           }else{
               throw new ValidationException("A user with this email is already registered");
           }
        }
        if (userForm.getNewBirthDay()!=null){
            userForm.setBirthday(userForm.getNewBirthDay());
        }
        if (userForm.getNewPassword()!=null){
            passwordValidate(userForm.getNewPassword());
            userForm.setPassword(userForm.getNewPassword());
        }
       userService.add(buildUser(userForm));
    }

    public void delete(long id){
        userService.delete(id);
    }
}

package com.online.diary.forms;

import com.online.diary.model.MomentsOfLife;
import com.online.diary.model.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDate;
import java.util.List;
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserForm {
    private Long id;
    private String username;
    private String newUsername;
    private String password;
    private String confirmPassword;
    private String newPassword;
    private String eMail;
    private String newEmail;
    private String role;
    private LocalDate birthday;
    private LocalDate newBirthDay;
    private MultipartFile fileData;
    private byte[] image;
    private List<MomentsOfLifeForm> momentsOfLives;

    public UserForm(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.eMail = user.getEMail();
        this.role = user.getRole();
        this.birthday = user.getBirthday();
        this.image = user.getImage();

        for (MomentsOfLife momentsOfLife : user.getMomentsOfLives()){
            MomentsOfLifeForm momentsOfLifeForm = new MomentsOfLifeForm();
            momentsOfLifeForm.setId(momentsOfLife.getId());
            momentsOfLifeForm.setTag(momentsOfLife.getTag());
            momentsOfLifeForm.setPublicationDate(momentsOfLife.getPublicationDate());
            momentsOfLifeForm.setText(momentsOfLife.getText());
            momentsOfLifeForm.setIsPrivate(momentsOfLife.getIsPrivate());
            momentsOfLifeForm.setIsApprovedForPublication(momentsOfLife.getIsApprovedForPublication());
            this.momentsOfLives.add(momentsOfLifeForm);
        }


            }


}

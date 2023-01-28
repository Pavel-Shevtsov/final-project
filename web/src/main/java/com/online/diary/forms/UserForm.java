package com.online.diary.forms;

import com.online.diary.model.Post;
import com.online.diary.model.User;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDate;
import java.util.ArrayList;
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
    private String email;
    private String newEmail;
    private String role;
    private LocalDate birthday;
    private LocalDate newBirthday;
    private MultipartFile fileData;
    private byte[] image;
    private List<PostForm> posts = new ArrayList<>();

    public UserForm(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.birthday = user.getBirthday();
        this.image = user.getImage();

        if (user.getPosts()!=null) {
            for (Post post : user.getPosts()) {
                PostForm postForm = new PostForm();
                postForm.setId(post.getId());
                postForm.setTag(post.getTag());
                postForm.setPublicationDate(post.getPublicationDate());
                postForm.setText(post.getText());
                postForm.setIsPrivate(post.getIsPrivate());
                postForm.setIsApprovedForPublication(post.getIsApprovedForPublication());
                this.posts.add(postForm);
            }
        }


            }


}

package com.online.diary.forms;
import com.online.diary.model.Post;
import com.online.diary.model.User;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class PostForm {
    private Long id;
    private String tag;
    private LocalDate publicationDate;
    private String text;
    private String newText;
    private Boolean isPrivate;
    private Boolean isApprovedForPublication;
    private User user;

    public PostForm(Post post){
        this.id = post.getId();
        this.tag = post.getTag();
        this.publicationDate = post.getPublicationDate();
        this.text = post.getText();
        this.isPrivate = post.getIsPrivate();
        this.isApprovedForPublication = post.getIsApprovedForPublication();
        this.user = post.getUser();
    }

}

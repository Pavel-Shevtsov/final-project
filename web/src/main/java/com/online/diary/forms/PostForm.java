package com.online.diary.forms;
import com.online.diary.entity.Post;
import com.online.diary.entity.User;
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
    private String newTag;
    private LocalDate publicationDate;
    private LocalDate lastUpdateDate;
    private String text;
    private String newText;
    private Boolean isPrivate;
    private Boolean isApprovedForPublication;
    private User user;

    public PostForm(Post post){
        this.id = post.getId();
        this.tag = post.getTag();
        this.publicationDate = post.getPublicationDate();
        this.lastUpdateDate = post.getLastUpdateDate();
        this.text = post.getText();
        this.isPrivate = post.getIsPrivate();
        this.isApprovedForPublication = post.getIsApprovedForPublication();
        this.user = post.getUser();
    }

}

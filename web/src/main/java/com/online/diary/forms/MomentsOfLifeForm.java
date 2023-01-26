package com.online.diary.forms;
import com.online.diary.model.MomentsOfLife;
import com.online.diary.model.User;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class MomentsOfLifeForm {
    private Long id;
    private String tag;
    private LocalDate publicationDate;
    private String text;
    private String newText;
    private Boolean isPrivate;
    private Boolean isApprovedForPublication;
    private User user;

    public MomentsOfLifeForm (MomentsOfLife momentsOfLife){
        this.id = momentsOfLife.getId();
        this.tag = momentsOfLife.getTag();
        this.publicationDate = momentsOfLife.getPublicationDate();
        this.text = momentsOfLife.getText();
        this.isPrivate = momentsOfLife.getIsPrivate();
        this.isApprovedForPublication = momentsOfLife.getIsApprovedForPublication();
        this.user = momentsOfLife.getUser();
    }

}

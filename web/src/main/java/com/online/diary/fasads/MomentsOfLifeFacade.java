package com.online.diary.fasads;

import com.online.diary.forms.MomentsOfLifeForm;
import com.online.diary.model.MomentsOfLife;
import com.online.diary.service.MOLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MomentsOfLifeFacade {

    @Autowired
    MOLService molService;

    public MomentsOfLife buildMomentOfLife(MomentsOfLifeForm momentsOfLifeForm){
        MomentsOfLife momentsOfLife = new MomentsOfLife();
        momentsOfLife.setId(momentsOfLifeForm.getId());
        momentsOfLife.setTag(momentsOfLifeForm.getTag());
        momentsOfLife.setText(momentsOfLifeForm.getText());
        momentsOfLife.setIsPrivate(momentsOfLifeForm.getIsPrivate());
        momentsOfLife.setPublicationDate(momentsOfLifeForm.getPublicationDate());
        momentsOfLife.setIsApprovedForPublication(momentsOfLifeForm.getIsApprovedForPublication());
        momentsOfLife.setUser(momentsOfLifeForm.getUser());
        return momentsOfLife;
    }

    public void add(MomentsOfLifeForm momentsOfLifeForm){
       molService.add(buildMomentOfLife(momentsOfLifeForm));
    }

    public MomentsOfLifeForm getById(long id){
        return new MomentsOfLifeForm(molService.getById(id));
    }

    public List<MomentsOfLife> getNotApproved(Boolean isPrivate,Boolean isApprovedForPublication ){
        return molService.getNotApprovedMoments(isPrivate,isApprovedForPublication);
    }

    public void update(MomentsOfLifeForm momentsOfLifeForm){
        if (momentsOfLifeForm.getNewText()!=null){
            momentsOfLifeForm.setText(momentsOfLifeForm.getNewText());
            if (!momentsOfLifeForm.getIsPrivate()){
                momentsOfLifeForm.setIsApprovedForPublication(null);
            }
        }
        molService.modify(buildMomentOfLife(momentsOfLifeForm));
    }

    public void delete(long id){
        molService.delete(id);
    }
}

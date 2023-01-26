package com.online.diary.service;

import com.online.diary.model.MomentsOfLife;

import java.util.List;

public interface MOLService extends IService<MomentsOfLife,Long>{
    List<MomentsOfLife> getNotApprovedMoments (Boolean isPrivate, Boolean isApprovedForPublication);
}

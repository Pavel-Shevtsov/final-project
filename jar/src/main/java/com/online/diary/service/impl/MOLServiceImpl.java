package com.online.diary.service.impl;

import com.online.diary.model.MomentsOfLife;
import com.online.diary.repository.MemoryOfLifeRepository;
import com.online.diary.service.MOLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MOLServiceImpl extends BaseServiceImpl<MomentsOfLife,Long> implements MOLService {

    @Autowired
    MemoryOfLifeRepository memoryOfLifeRepository;

    @Override
    public List<MomentsOfLife> getNotApprovedMoments(Boolean isPrivate, Boolean isApprovedForPublication) {
        return memoryOfLifeRepository.findByIsPrivateAndIsApprovedForPublication(isPrivate,isApprovedForPublication);
    }
}

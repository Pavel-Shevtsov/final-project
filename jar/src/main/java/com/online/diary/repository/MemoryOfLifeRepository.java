package com.online.diary.repository;

import com.online.diary.model.MomentsOfLife;

import java.util.List;

public interface MemoryOfLifeRepository extends BaseRepository<MemoryOfLifeRepository,Long> {
    List<MomentsOfLife> findByIsPrivateAndIsApprovedForPublication(boolean isPrivate, Boolean isApprovedForPublication);

}

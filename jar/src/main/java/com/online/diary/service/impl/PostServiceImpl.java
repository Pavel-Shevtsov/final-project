package com.online.diary.service.impl;

import com.online.diary.model.Post;
import com.online.diary.repository.PostJpaRepository;
import com.online.diary.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl extends BaseServiceImpl<Post,Long> implements PostService {

    @Autowired
    PostJpaRepository postJpaRepository;

    @Override
    public List<Post> getPostApprovedOrNo(Boolean isPrivate, Boolean isApprovedForPublication) {
        return postJpaRepository.findByIsPrivateAndIsApprovedForPublication(isPrivate,isApprovedForPublication);
    }

}

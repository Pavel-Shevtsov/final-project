package com.online.diary.service;

import com.online.diary.model.Post;

import java.util.List;

public interface PostService extends IService<Post,Long>{
    List<Post> getNotApprovedMoments (Boolean isPrivate, Boolean isApprovedForPublication);
}

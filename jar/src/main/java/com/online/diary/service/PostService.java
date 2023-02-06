package com.online.diary.service;

import com.online.diary.entity.Post;

import java.util.List;

public interface PostService extends BaseService<Post,Long> {
    List<Post> getPostApprovedOrNo (Boolean isPrivate, Boolean isApprovedForPublication);
}

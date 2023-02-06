package com.online.diary.repository;

import com.online.diary.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostJpaRepository extends BaseRepository<Post,Long> {
    List<Post> findByIsPrivateAndIsApprovedForPublication(boolean isPrivate, Boolean isApprovedForPublication);

}

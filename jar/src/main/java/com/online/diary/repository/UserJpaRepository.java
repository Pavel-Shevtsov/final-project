package com.online.diary.repository;

import com.online.diary.model.User;

import java.util.Optional;


public interface UserJpaRepository extends BaseRepository<User, Long> {
    Optional <User> findByUsername(String userName);
    Optional <User> findByEmail(String email);

}

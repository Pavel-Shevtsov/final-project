package com.online.diary.repository;

import com.online.diary.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends BaseRepository<User, Long> {
    Optional <User> findByUsername(String userName);
    Optional <User> findByEmail(String email);

}

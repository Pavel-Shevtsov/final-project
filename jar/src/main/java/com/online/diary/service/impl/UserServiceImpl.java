package com.online.diary.service.impl;
import com.online.diary.entity.User;
import com.online.diary.repository.UserJpaRepository;
import com.online.diary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User,Long> implements UserService {

    @Autowired
    UserJpaRepository userJpaRepositoryService;

    @Override
    public User getByUsername(String userName) {
       return userJpaRepositoryService.findByUsername(userName).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return userJpaRepositoryService.findByEmail(email).orElse(null);
    }
}

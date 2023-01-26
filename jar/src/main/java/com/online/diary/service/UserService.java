package com.online.diary.service;

import com.online.diary.model.User;

public interface UserService extends IService<User,Long> {
   User getByUsername(String userName);
   User getByEmail(String email);
}

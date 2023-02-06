package com.online.diary.service;

import com.online.diary.entity.User;

public interface UserService extends BaseService<User,Long> {
   User getByUsername(String userName);
   User getByEmail(String email);
}

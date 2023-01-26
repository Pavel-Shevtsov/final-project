package com.online.diary.repository;

import com.online.diary.config.TestAppContext;
import com.online.diary.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestAppContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTest {
    @Autowired
    UserJpaRepository userJpaRepository;

}

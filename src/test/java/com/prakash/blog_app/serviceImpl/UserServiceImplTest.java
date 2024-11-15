package com.prakash.blog_app.serviceImpl;

import com.prakash.blog_app.repositories.UserRepository;
import com.prakash.blog_app.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository mockUserRepository;


    UserService userService;

    @BeforeEach
    void setUp() {
        userService=new UserServiceImpl(mockUserRepository);
    }

    @Test
    void getAllUsers() {

        userService.getAllUsers();
        Mockito.verify(mockUserRepository).findAll();

    }
}
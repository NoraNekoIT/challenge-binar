package com.noranekoit.challenge4.Service;

import com.noranekoit.challenge4.models.entity.Users;
import com.noranekoit.challenge4.payload.request.InsertUserRequest;
import com.noranekoit.challenge4.repository.UsersRepository;
import com.noranekoit.challenge4.service.impl.UsersServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UsersRepository usersRepository;

    private UsersServiceImpl usersService;

    @BeforeEach void setUp() {
        this.usersService = new UsersServiceImpl(this.usersRepository);
    }

    @Test void getAllUsers() {
        usersService.getAllUsers();
        Mockito.verify(usersRepository, Mockito.times(1)).findAll();
    }

    @Test void deleteUserByUsername() {
        usersService.deleteUserByUsername(1L);
        Mockito.verify(usersRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(usersRepository, Mockito.atLeast(0)).deleteById(1L);
        Mockito.verify(usersRepository, Mockito.times(0)).deleteById(1L);
    }

    @Test void addUser() {
       InsertUserRequest insert = new InsertUserRequest(
               "noranekoit99",
               "admin123",
               null,
               "bunsmart99@gmail.com"
       );
        usersService.addUser(insert);
        Users user = new Users(
                insert.getUsername(),
                insert.getPassword(),
                insert.getEmail()
        );
        Mockito.lenient().when(usersRepository.save(user)).thenReturn(user);

        Assertions.assertEquals("noranekoit99", user.getUsername());

    }

}
package com.noranekoit.challenge4.service;

import com.noranekoit.challenge4.error.NotFoundException;
import com.noranekoit.challenge4.payload.request.InsertUserRequest;
import com.noranekoit.challenge4.payload.request.UpdateUserRequest;
import com.noranekoit.challenge4.payload.response.UserInfoResponse;

import java.util.stream.Stream;

public interface UsersService {
    UserInfoResponse addUser(InsertUserRequest insertUserRequest);
//    Users getUserByUsername(String username);
    UserInfoResponse updateUser(Long idUser, UpdateUserRequest updateUserRequest) throws NotFoundException;

    void deleteUserByUsername(Long idUser) throws NotFoundException;

    Stream<UserInfoResponse> getAllUsers();
}

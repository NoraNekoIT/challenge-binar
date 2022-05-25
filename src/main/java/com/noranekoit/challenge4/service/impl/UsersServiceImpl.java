package com.noranekoit.challenge4.service.impl;

import com.noranekoit.challenge4.models.entity.Role;
import com.noranekoit.challenge4.models.entity.Users;
import com.noranekoit.challenge4.error.NotFoundException;
import com.noranekoit.challenge4.payload.request.InsertUserRequest;
import com.noranekoit.challenge4.payload.request.UpdateUserRequest;
import com.noranekoit.challenge4.payload.response.UserInfoResponse;
import com.noranekoit.challenge4.repository.UsersRepository;
import com.noranekoit.challenge4.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    //insert user
    @Override
    public UserInfoResponse addUser(InsertUserRequest insertUserRequest) {
        Users user = new Users(
                insertUserRequest.getUsername(),
                insertUserRequest.getPassword(),
                insertUserRequest.getEmail()
        );
        usersRepository.save(user);
        return convertUsertoUserResponse(user);
    }


    @Override
    public UserInfoResponse updateUser(Long idUser, UpdateUserRequest updateUserRequest) throws NotFoundException {
        Users usersDb = usersRepository.findById(idUser).orElseThrow(() -> new NotFoundException());
        usersDb.setPassword(updateUserRequest.getPassword());
        usersDb.setEmail(updateUserRequest.getEmail());
        return convertUsertoUserResponse(usersDb);
    }

    @Override
    public void deleteUserByUsername(Long idUser) throws NotFoundException {
        Users users = usersRepository.findById(idUser).orElseThrow(() -> new NotFoundException());
        usersRepository.deleteById(idUser);
    }

    @Override
    public Stream<UserInfoResponse> getAllUsers() {
        List<Users> users = usersRepository.findAll();
        return users.stream().map(this::convertUsertoUserResponse);
    }

    private UserInfoResponse convertUsertoUserResponse(Users user) {

        return new UserInfoResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles().stream().map(
                        String::valueOf
                ).collect(Collectors.toList())
        );
    }


    //insert user
//    @Override
//    public Users addUser(Users user) {
//        return usersRepository.save(user);
//    }


    //get user by username
//    @Override
//    public Users getUserByUsername(String username) {
//        return usersRepository.findById(username).get();
//    }

    //update user
//    @Override
//    public void updateUser(Users user) {
//        //cek jika ada username di database
//        Users usersDB = usersRepository.findById(user.getUsername()).orElseThrow();
//        //jika ada update
//        usersRepository.save(user);
//    }

    //delete user
//    @Override
//    public void deleteUserByUsername(String username) {
//        try {
//            usersRepository.deleteById(username);
//        }catch (DataAccessException ex){
//            throw  new RuntimeException(ex.getMessage());
//        }
//    }

    //getAllUser
//    @Override
//    public List<Users> getAllUsers() {
//        return usersRepository.findAll();
//    }
}

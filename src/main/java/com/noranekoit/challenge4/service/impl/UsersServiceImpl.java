package com.noranekoit.challenge4.service.impl;
import com.noranekoit.challenge4.models.entity.Users;
import com.noranekoit.challenge4.error.NotFoundException;
import com.noranekoit.challenge4.payload.request.InsertUserRequest;
import com.noranekoit.challenge4.payload.request.UpdateUserRequest;
import com.noranekoit.challenge4.payload.response.UserInfoResponse;
import com.noranekoit.challenge4.repository.UsersRepository;
import com.noranekoit.challenge4.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

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
        Users usersDb = usersRepository.findById(idUser).orElseThrow(NotFoundException::new);
        usersDb.setPassword(updateUserRequest.getPassword());
        usersDb.setEmail(updateUserRequest.getEmail());
        return convertUsertoUserResponse(usersDb);
    }

    @Override
    public void deleteUserByUsername(Long idUser) {
        if (usersRepository.findById(idUser).isPresent()){
            usersRepository.deleteById(idUser);
        }
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
                ).toList()
        );
    }
}

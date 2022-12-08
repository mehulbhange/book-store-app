package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.entity.User;

import java.util.List;

public interface IUserService {
    User createUser(UserDTO userDTO);
    User getUserById(Long userId);

    User getUserByToken(String token);

    List<User> getUsers();

    User updateUser(Long id, UserDTO userDTO);

    String deleteUser(Long id);

    String userLogin(LoginDTO loginDTO);
}

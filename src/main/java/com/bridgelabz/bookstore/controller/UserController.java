package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin( allowedHeaders = "*" , origins = "*")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     *
     * @param userDTO
     * @return
     */
    @PostMapping("/user")
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO userDTO){
        User user = userService.createUser(userDTO);
        ResponseDTO respDTO = new ResponseDTO("User created!", user);
        return new ResponseEntity<>(respDTO, HttpStatus.CREATED);
    }

    /**
     *
     * @return
     */
    @GetMapping("/users")
    public ResponseEntity<ResponseDTO> getAllUsers(){
        List<User> users = userService.getUsers();
        ResponseDTO respDTO = new ResponseDTO("All users list",users);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        ResponseDTO respDTO = new ResponseDTO("User fetched by id", user);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     *
     * @param token
     * @return
     */
    @GetMapping("/user-token/{token}")
    public ResponseEntity<ResponseDTO> getUserByToken(@PathVariable("token") String token){
        User user = userService.getUserByToken(token);
        ResponseDTO respDTO = new ResponseDTO("User fetched by token", user);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param userDTO
     * @return
     */
    @PutMapping("/update-user/{id}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable("id") Long id , @RequestBody UserDTO userDTO){
        User user = userService.updateUser(id, userDTO);
        ResponseDTO respDTO = new ResponseDTO("User updated with id "+ id, user);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        ResponseDTO respDTO = new ResponseDTO("User deleted with id "+ id, null);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    /**
     *
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody LoginDTO loginDTO){
        String token = userService.userLogin(loginDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }


}

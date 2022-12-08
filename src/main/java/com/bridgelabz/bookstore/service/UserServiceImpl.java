package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.entity.User;
import com.bridgelabz.bookstore.exception.UserAuthenticationException;
import com.bridgelabz.bookstore.exception.UserNotFoundException;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.utility.TokenUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.websocket.AuthenticationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUserService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtility tokenUtility;


    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM YYYY");

    @Override
    public User createUser(UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        User user = modelMapper.map(userDTO, User.class);
        user.setRegisteredDate(LocalDate.now());
        user.setUpdatedDate(LocalDate.now());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found with id : "+ userId));
    }

    @Override
    public User getUserByToken(String token) {
        return userRepository.findById(tokenUtility.decodeLoginToken(token)).orElse(null);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow( () -> new UserNotFoundException("user not found with id : "+ id));
        if (user != null){
            userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            modelMapper.map(userDTO, user);
            user.setUpdatedDate(LocalDate.now());
            user = userRepository.save(user);
            log.info("This is user after mapping : " + user);
        }
        return user;
    }

    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "Deleted user with id : "+ id;
    }

    @Override
    public String userLogin(LoginDTO loginDTO){
        User user =userRepository.findByEmail(loginDTO.getEmail());
        if (user != null && bCryptPasswordEncoder.matches(loginDTO.getPassword(),user.getPassword())){
            //return tokenUtility.generateLoginToken(loginDTO);
            return tokenUtility.generateLoginToken(user.getUserId());
        }else{
            throw new UserAuthenticationException("Authentication Error ");
        }
    }
}

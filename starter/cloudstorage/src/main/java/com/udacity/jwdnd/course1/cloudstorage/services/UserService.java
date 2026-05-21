package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public int createUser(User user) {
        if (userMapper.getUser(user.getUsername()) != null) {
            return -1;
        }

        String salt = hashService.getRandomSalt();
        String hashedPassword = hashService.getHashedValue(user.getPassword(), salt);

        userMapper.insert(new User(null, user.getUsername(), salt, hashedPassword,
                user.getFirstname(), user.getLastname()));

        return 1;
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}

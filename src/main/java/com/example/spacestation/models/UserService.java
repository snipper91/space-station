package com.example.spacestation.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String username) {
        return userRepository.findOne(username);
    }

    public boolean updateUser(String username, String password, String phrase) {

        if (userRepository.exists(username)) {
            User user = userRepository.findByUsername(username);
            if (user.getPhrase().equals(phrase)) {
                user.setPassword(password);
                userRepository.save(user);
                return true;
            } else{
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean addUser(User user) {

        if(userRepository.exists(user.getUsername())) {
            return false;
        } else {
            userRepository.save(user);
            return true;
        }
    }

    public boolean loginUser(User user) {
        if (userRepository.findOne(user.getUsername()).equals(user)) {
            return true;
        } else {
            return false;
        }
    }

    public void updatePassword(User user) {

    }
}

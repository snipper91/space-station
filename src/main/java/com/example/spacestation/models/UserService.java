package com.example.spacestation.models;

import com.example.spacestation.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    public User getUser(String username, UserDao userDao) {
        return userDao.findByUsername(username);
    }

    public boolean addUser(User user, UserDao userDao) {

        if(userDao.findByUsername(user.getUsername()) != null) {
            return false;
        } else {
            userDao.save(user);
            return true;
        }
    }

    public boolean loginUser(User user, UserDao userDao) {
        User existingUser = userDao.findByUsername(user.getUsername());
        if (existingUser.getPassword().equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public  boolean checkPassword(User user) {
        if (user.getPassword().equals(user.getVerifyPassword())) {
            return true;
        } else {
            return false;
        }
    }
}

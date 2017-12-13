package com.example.spacestation.controllers;

import com.example.spacestation.models.User;
import com.example.spacestation.models.UserService;
import com.example.spacestation.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute(new User());
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(Model model, @ModelAttribute @Valid User user, HttpServletRequest request) {
        boolean isNewUser;
        boolean isPassword;
        UserService userService = new UserService();
        isNewUser = userService.addUser(user, userDao);
        isPassword = userService.checkPassword(user);

        if (isNewUser) {
            if (isPassword) {
                request.getSession().setAttribute("username", user.getUsername());
                return "redirect:/";
            } else {
                model.addAttribute("passwordError", "Passwords do not match.");
                return "signup";
            }
        } else {
            model.addAttribute("userError", "User already exists.");
            return "signup";
        }

    }
}

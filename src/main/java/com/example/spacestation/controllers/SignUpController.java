package com.example.spacestation.controllers;

import com.example.spacestationunsecured.models.User;
import com.example.spacestationunsecured.models.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SignUpController {

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(Model model, @ModelAttribute @Valid User user, HttpServletRequest request) {
        boolean isNewUser;
        UserService userService = new UserService();
        isNewUser = userService.addUser(user);

        if (isNewUser) {
            request.getSession().setAttribute("username", user.getUsername());
            return "redirect: ";
        } else {
            model.addAttribute("error", "User already exists.");
            return "signup";
        }

    }
}

package com.example.spacestation.controllers;

import com.example.spacestationunsecured.models.User;
import com.example.spacestationunsecured.models.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, @ModelAttribute User user, HttpServletRequest request) {
        UserService userService = new UserService();
        if (userService.loginUser(user)) {
            request.getSession().setAttribute("username", user.getUsername());
            return "redirect: ";
        } else {
            model.addAttribute("error", "Incorrect username or password.");
            return "login";
        }
    }
}

package com.example.spacestation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @RequestMapping(value = "")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("userSession") != null) {
            String username = session.getAttribute("username").toString();
            model.addAttribute("username", username);
            return "index";
        } else {
            return "redirect: /login";
        }

    }
}

package com.example.spacestation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LocationController {

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public String location(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            //TODO get information from ISS api fro current position
            return "location";
        } else {
            return "redirect:/login";
        }
    }
}

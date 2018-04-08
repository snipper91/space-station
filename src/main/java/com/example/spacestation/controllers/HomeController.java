package com.example.spacestation.controllers;

import com.example.spacestation.models.URLConnection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class HomeController {

    @RequestMapping(value = "")
    public String index(Model model, HttpServletRequest request) {

        URLConnection urlConnection = new URLConnection();

        try {
            String currentLocation = "The current position of the ISS is: " + urlConnection.getCurrentLocation() + ".";
            model.addAttribute("location", currentLocation);
        } catch (IOException ie) {
            System.out.println("Failed to retrieve the ISS current position.");
        }
        return "index";
    }
}

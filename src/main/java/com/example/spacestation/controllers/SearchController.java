package com.example.spacestation.controllers;

import com.example.spacestation.models.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SearchController {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            //TODO add search history to model
            return "search";
        } else {
            return "redirect: /login";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String newSearch(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            //TODO return search information
            return "search";
        } else {
            return "redirect: /login";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.DELETE)
    public String deleteSearch(HttpServletRequest request, @RequestParam Integer searchId) {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            SearchService searchService = new SearchService();
            searchService.deleteSearch(searchId);
            return "search";
        } else {
            return "redirect: /login";
        }
    }
}

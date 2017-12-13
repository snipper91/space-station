package com.example.spacestation.controllers;

import com.example.spacestation.models.*;
import com.example.spacestation.models.data.SearchDao;
import com.example.spacestation.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class SearchController {

    @Autowired
    UserDao userDao;

    @Autowired
    SearchDao searchDao;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            UserService userService = new UserService();
            SearchService searchService = new SearchService();
            ArrayList<Search> searches = new ArrayList<>();
            searches = searchService.getAllSearches(userService.getUser(session.getAttribute("username").toString(), userDao), searchDao);
            model.addAttribute("searches", searches);
            model.addAttribute(new Address());
            return "search";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String newSearch(Model model, HttpServletRequest request, @ModelAttribute @Valid Address address) {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            SearchService searchService = new SearchService();
            URLConnection urlConnection = new URLConnection(address);
            try {
                boolean isGoogleRequest = urlConnection.googleRequest();

                if (isGoogleRequest) {
                    boolean isISSRequest = urlConnection.issRequest();
                    if (isISSRequest) {
                        model.addAttribute("time", urlConnection.getTime());
                        UserService userService = new UserService();
                        Search search = new Search();
                        search.setAddress(address);
                        search.setTime(urlConnection.getTime());
                        search.setUser(userService.getUser(session.getAttribute("username").toString(), userDao));
                        searchService.addSearch(search, searchDao);
                        model.addAttribute(new Address());
                        return "search";
                    } else {
                        model.addAttribute("error", "Something went wrong with the ISS connection.");
                        model.addAttribute(new Address());
                        return "search";
                    }
                } else {
                model.addAttribute("error", "Something went wrong with the Google connection.");
                model.addAttribute(new Address());
                return "search";
                }
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/search", method = RequestMethod.DELETE)
    public String deleteSearch(HttpServletRequest request, @RequestParam Integer searchId) {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            SearchService searchService = new SearchService();
            searchService.deleteSearch(searchId, searchDao);
            return "search";
        } else {
            return "redirect:/login";
        }
    }
}

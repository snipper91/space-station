package com.example.spacestation.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SearchService {

    @Autowired
    private SearchRepository searchRepository;

    public ArrayList<Search> getAllSearches(String username) {
        return searchRepository.findByUsername(username);
    }

    public Search getSearch(Integer searchId) {
        return searchRepository.findOne(searchId);
    }

    public void addSearch(Search search) {
        searchRepository.save(search);
    }

    public void deleteSearch(Integer searchId) {
        searchRepository.delete(searchId);
    }
}

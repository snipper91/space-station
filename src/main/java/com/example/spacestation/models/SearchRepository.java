package com.example.spacestation.models;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface SearchRepository extends CrudRepository<Search, Integer> {
    public ArrayList<Search> findByUsername(String username);
}

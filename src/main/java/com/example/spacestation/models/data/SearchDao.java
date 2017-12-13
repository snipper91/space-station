package com.example.spacestation.models.data;

import com.example.spacestation.models.Search;
import com.example.spacestation.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface SearchDao extends CrudRepository<Search, Integer> {
    ArrayList<Search> findByUserId(int userId);
}

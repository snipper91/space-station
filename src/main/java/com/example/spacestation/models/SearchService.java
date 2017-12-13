package com.example.spacestation.models;

import com.example.spacestation.models.data.SearchDao;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;

public class SearchService {

    public ArrayList<Search> getAllSearches(User user, SearchDao searchDao) { return searchDao.findByUser(user); }

    public Search getSearch(Integer searchId, SearchDao searchDao) {
        return searchDao.findOne(searchId);
    }

    public void addSearch(Search search, SearchDao searchDao) {
        searchDao.save(search);
    }

    public void deleteSearch(Integer searchId, SearchDao searchDao) {
        searchDao.delete(searchId);
    }


}

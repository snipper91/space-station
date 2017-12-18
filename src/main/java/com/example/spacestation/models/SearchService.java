package com.example.spacestation.models;

import com.example.spacestation.models.data.SearchDao;
import com.example.spacestation.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;

public class SearchService {

    public Search getSearch(Integer searchId, SearchDao searchDao) {
        return searchDao.findOne(searchId);
    }

    public void addSearch(Search search, SearchDao searchDao) {
        searchDao.save(search);
    }

    public ArrayList<Search> getByUsername(String username, SearchDao searchDao, UserDao userDao) {
        int userId = userDao.findByUsername(username).getId();
        return searchDao.findByUserId(userId);
    }

    public void deleteSearch(Integer searchId, SearchDao searchDao) {
        searchDao.delete(searchId);
    }

    public void deleteMultiple(ArrayList<Integer> ids, SearchDao searchDao) {
        for(int id : ids) {
            searchDao.delete(id);
        }
    }


}

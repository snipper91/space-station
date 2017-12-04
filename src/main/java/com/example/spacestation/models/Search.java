package com.example.spacestation.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.HashMap;

@Entity
public class Search {

    @Id
    private Integer searchId;
    @ManyToOne
    private String username;
    private HashMap<String, Integer> location = new HashMap<>();
    private Date date = new Date();
    private int time;
}

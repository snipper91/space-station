package com.example.spacestation.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.HashMap;

@Entity
public class Search {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private User user;
    private String time;
    private String address;

    public Search() { }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(Address address) {

        this.address = address.getStreetNumber() + " " + address.getStreet() + " " + address.getCity() + " " + address.getState();
    }

    public String getAddressURL() {
        return address.replaceAll(" ", "+");
    }

    public void setUser(User user) {
        this.user = user;
    }

}

package com.example.spacestation.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class User {

    @Id
    @NotNull
    @Size(min = 3, max = 15)
    private String username;
    @NotNull
    @Size(min = 3, max = 15)
    private String password;
    private String phrase;
    @OneToMany
    private ArrayList<Integer> searchIds;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Integer> getSearchIds() {
        return searchIds;
    }

    public void setSearchIds(ArrayList<Integer> searchIds) {
        this.searchIds = searchIds;
    }

    public String getPhrase() { return phrase; }

    public void setPhrase(String phrase) { this.phrase = phrase; }

    public static boolean validateUsername(User user) {
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(user.getUsername());
        boolean containsSpecial = m.find();

        if (containsSpecial) {
            return false;
        } else {
            return true;
        }
    }
}

package com.example.spacestation.models;

import javax.validation.constraints.NotNull;

public class Address {

    @NotNull
    private String streetNumber;
    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    private String state;

    public Address() {}

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        String streetString = this.street.replaceAll(" ", "+");
        String cityString = this.city.replaceAll(" ", "+");
        return streetNumber + "+" + streetString + "+"+ cityString + "+" + state;
    }
}

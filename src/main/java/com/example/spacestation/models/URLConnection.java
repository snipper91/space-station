package com.example.spacestation.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import org.json.JSONObject;

public class URLConnection {
    private String address;
    private long riseTime;
    private long duration;
    private JSONObject location = new JSONObject();
    private static final String USER_AGENT = "Mozilla/5.0";
    private final String GOOGLE_KEY = "AIzaSyCmEoMk6KpWJUT11v3aJ171eE_QCYXEpmQ";
    private final String GOOGLE_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private String addressURL;

    public URLConnection(Address address) {
        this.address = address.toString();
    }

    public URLConnection(String addressURL) { this.addressURL = addressURL; }

    public String getTime() {
        java.util.Date risetimeReadable = new java.util.Date(riseTime*1000);
        String time = "The ISS will rise at " + risetimeReadable + " and be visible for " + duration + " seconds.";
        return time;
    }

    public boolean googleRequest() throws IOException {
        URL obj = new URL(GOOGLE_URL + address + "&key=" + GOOGLE_KEY);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject jsonObject = new JSONObject(response.toString());
            this.location = jsonObject.getJSONArray("results").getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location");
            return true;
        } else {
            return false;
        }
    }

    public boolean issRequest() throws IOException {
        String issUrl = "http://api.open-notify.org/iss-pass.json?lat="
                + location.getDouble("lat") + "&lon=" + location.getDouble("lng");
        URL obj = new URL(issUrl);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject jsonObject = new JSONObject(response.toString());
            this.duration = jsonObject.getJSONArray("response").getJSONObject(0).getLong("duration");
            this.riseTime = jsonObject.getJSONArray("response").getJSONObject(0).getLong("risetime");
            return true;
        } else {
            return false;
        }
    }

    public void updatedSearch() throws IOException {
        URL obj = new URL(GOOGLE_URL + addressURL + "&key=" + GOOGLE_KEY);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject jsonObject = new JSONObject(response.toString());
            this.location = jsonObject.getJSONArray("results").getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location");

        }
        String issUrl = "http://api.open-notify.org/iss-pass.json?lat="
                + location.getDouble("lat") + "&lon=" + location.getDouble("lng");
        URL object = new URL(issUrl);
        HttpURLConnection connectionURL = (HttpURLConnection) object.openConnection();
        connectionURL.setRequestMethod("GET");
        connectionURL.setRequestProperty("User-Agent", USER_AGENT);
        responseCode = connectionURL.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {

            BufferedReader inSecond = new BufferedReader(new InputStreamReader(connectionURL.getInputStream()));
            String inputLine;
            StringBuffer responseSecond = new StringBuffer();
            while ((inputLine = inSecond.readLine()) != null) {
                responseSecond.append(inputLine);
            }
            inSecond.close();
            JSONObject jsonObject = new JSONObject(responseSecond.toString());
            this.duration = jsonObject.getJSONArray("response").getJSONObject(0).getLong("duration");
            this.riseTime = jsonObject.getJSONArray("response").getJSONObject(0).getLong("risetime");
        }
    }
}

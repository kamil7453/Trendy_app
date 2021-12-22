package com.example.projekt.model;

import java.util.List;

public class Service {
    public String name;
    public String duration;
    public String price;
    public String description;
    public Salon salon;
    public List<String> hashtags;

    public Service() {
    }

    public Service(String name, String duration, String price, String description, Salon salon, List<String> hashtags) {
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.description = description;
        this.salon = salon;
        this.hashtags = hashtags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }
}

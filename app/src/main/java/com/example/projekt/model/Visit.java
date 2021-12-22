package com.example.projekt.model;

public class Visit {
    public Salon salon;
    public Service service;
    public String userId;
    public String date;
    public String hour;

    public Visit() {
    }

    public Visit(Salon salon, Service service, String userId, String date, String hour) {
        this.salon = salon;
        this.service = service;
        this.userId = userId;
        this.date = date;
        this.hour = hour;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}

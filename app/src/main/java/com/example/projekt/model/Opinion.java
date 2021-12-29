package com.example.projekt.model;

import com.google.firebase.Timestamp;

public class Opinion {
    private String opinion;
    private String salon;
    private Timestamp date;
    private String user;
    private String picture;

    public Opinion() {
    }

    public Opinion(String opinion, String salon, Timestamp date, String user) {
        this.opinion = opinion;
        this.salon = salon;
        this.date = date;
        this.user = user;
        this.picture = "imgOpinion/haircut_3.jpg";
    }//4arg

    public Opinion(String opinion, String salon, Timestamp date, String user, String picture) {
        this.opinion = opinion;
        this.salon = salon;
        this.date = date;
        this.user = user;
        this.picture = picture;
    }//5arg

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}


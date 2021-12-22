package com.example.projekt.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Salon implements Parcelable {
    public String name;
    public String email;
    public String street;
    public String city;
    public int phoneNumber;
    public String photo;

    public Salon() {
    }

    public Salon(String name, String email, String street, String city, int phoneNumber, String photo) {
        this.name = name;
        this.email = email;
        this.street = street;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
    }

    protected Salon(Parcel in) {
        name = in.readString();
        email = in.readString();
        street = in.readString();
        city = in.readString();
        phoneNumber = in.readInt();
        photo = in.readString();
    }

    public static final Creator<Salon> CREATOR = new Creator<Salon>() {
        @Override
        public Salon createFromParcel(Parcel in) {
            return new Salon(in);
        }

        @Override
        public Salon[] newArray(int size) {
            return new Salon[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(street);
        dest.writeString(city);
        dest.writeInt(phoneNumber);
        dest.writeString(photo);
    }
}

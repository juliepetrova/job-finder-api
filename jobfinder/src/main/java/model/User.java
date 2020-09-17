package model;

import java.util.Date;

public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private String city;
    private String country;
    private String email;
    private String username;
    private Role role_id;
    private String phone_number;

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    private String hashed_password;

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public User(){}

    public User(int id, String first_name, String last_name, String email, String username, String date_of_birth, String city, String country, String phone) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.city = city;
        this.country = country;
        this.email = email;
        this.username = username;
        this.phone_number = phone;
    }

    public User(String first_name, String last_name, String email, String username, String date_of_birth, String city, String country, String phone) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.city = city;
        this.country = country;
        this.email = email;
        this.username = username;
        this.phone_number = phone;
    }




}

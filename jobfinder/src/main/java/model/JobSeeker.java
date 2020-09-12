package model;

import java.util.Date;

public class JobSeeker extends User {

    private String skills;
    private String past_experience;
    private String picture;
    private double rating;

    public JobSeeker(int id, String first_name, String last_name, Date date_of_birth, String city, String country, String email, String skills, String past_experience, String picture, double rating) {
        super(id, first_name, last_name, date_of_birth, city, country, email);
        this.skills = skills;
        this.past_experience = past_experience;
        this.picture = picture;
        this.rating = rating;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPast_experience() {
        return past_experience;
    }

    public void setPast_experience(String past_experience) {
        this.past_experience = past_experience;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}

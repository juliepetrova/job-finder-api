package com.webapp.ui.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "applicant")
public class Applicant implements com.webapp.ui.model.base.Entity{

    @Id
    @Column(name = "user_id")
    private int id;
    private String picture;
    private String skills;
    private String experience;
    private double rating;

//    @OneToOne
//    @MapsId
//    private User user;

    public Set<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(Set<JobApplication> jobApplications) {
        this.jobApplications = jobApplications;
    }

    @OneToMany(mappedBy="applicant", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<JobApplication> jobApplications;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


}

package model;

import java.util.Date;

public class Job {
    private int id;
    private int user_id;
    private String title;
    private String description;
    private String address;
    private Date date_of_submition;
    private int working_hours;
    private double payment;
    private Status status_id;

    public Status getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Status status_id) {
        this.status_id = status_id;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate_of_submition() {
        return date_of_submition;
    }


    public int getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(int working_hours) {
        this.working_hours = working_hours;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public Job(int user_id, String title, String description, String address, Date date_of_submition, int working_hours, double payment) {
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.date_of_submition = date_of_submition;
        this.working_hours = working_hours;
        this.payment = payment;
    }

    public Job(int id, int user_id, String title, String description, String address, Date date_of_submition, int working_hours, double payment) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.date_of_submition = date_of_submition;
        this.working_hours = working_hours;
        this.payment = payment;
    }
}

package model;

import java.util.Date;

public class Application {
    private int job_id;
    private int applicant_id;
    private String description;
    private Date date_of_application;
    private Status status_id;

    public int getJob_id() {
        return job_id;
    }

    public int getApplicant_id() {
        return applicant_id;
    }

    public String getDescription() {
        return description;
    }


    public Date getDate_of_application() {
        return date_of_application;
    }

    public Status getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Status status_id) {
        this.status_id = status_id;
    }

    public Application(int job_id, int applicant_id, String description, Date date_of_application, Status status_id) {
        this.job_id = job_id;
        this.applicant_id = applicant_id;
        this.description = description;
        this.date_of_application = date_of_application;
        this.status_id = status_id;
    }
}

package com.webapp.ui.model;

import javax.persistence.*;

@Entity
@Table(name = "job_report")
public class JobReport implements com.webapp.ui.model.base.Entity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String description;
    private String date;

    @ManyToOne
    @JoinColumn(name="job_id", nullable=false)
    private Job job;

    @Column(name = "description", nullable = false, length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "date", nullable = false, length = 25)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}

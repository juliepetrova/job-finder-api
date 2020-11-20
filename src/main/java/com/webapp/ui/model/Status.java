package com.webapp.ui.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @Column(name = "id")
    private int id;

    private String name;
}

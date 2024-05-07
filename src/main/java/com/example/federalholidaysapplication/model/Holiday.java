package com.example.federalholidaysapplication.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Holiday", uniqueConstraints = { @UniqueConstraint(columnNames = {"country", "name"})})
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String country;
    private String name;
    private Date date;

    public Holiday() {

    }

    public Holiday(String country, String name, Date date) {
        this.country = country;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

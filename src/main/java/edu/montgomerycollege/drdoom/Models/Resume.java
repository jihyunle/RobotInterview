package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.util.Set;

@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @Null
    @Lob
    private String resumeData;

    private String resumeVersionName; // this is essentially ID, but with name for faster search

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    //=================================================
    //Constructors
    //=================================================
    //default
    public Resume() {

    }

    //loaded

//    public Resume(@Null String resumeData) {
//        this.resumeData = resumeData;
//    }


    public Resume(String resumeData){
        this.resumeData = resumeData;
    }


    //=================================================
    //Getters and setters
    //=================================================


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResumeData() {
        return resumeData;
    }

    public void setResumeData(String resumeData) {
        this.resumeData = resumeData;
    }


    public String getResumeVersionName() {
        return resumeVersionName;
    }

    public void setResumeVersionName(String resumeVersionName) {
        this.resumeVersionName = resumeVersionName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
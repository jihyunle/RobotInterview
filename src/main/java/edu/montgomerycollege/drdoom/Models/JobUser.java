package edu.montgomerycollege.drdoom.Models;


import javax.persistence.*;
import java.util.Set;

@Entity
public class JobUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String appStatus;

    private boolean matched;
    // if matched returns true, instantiate JUI obj

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "jobUser")//removed cascadeType=ALL
    private Set<JobUser_Interview> jobUser_interviews;

    //=================================================
    //Constructors
    //=================================================
    //default
    public JobUser(){

    }

    //loaded
    public JobUser(String appStatus, boolean matched) {
        this.appStatus = appStatus;
        this.matched = matched;
    }

    //other
    public JobUser(Job job, User user, String appStatus, boolean matched) {
        this.job = job;
        this.user = user;
        this.appStatus = appStatus;
        this.matched = matched;
    }

    //=================================================
    //Getters and Setters
    //=================================================
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public Set<JobUser_Interview> getJobUser_interviews() {
        return jobUser_interviews;
    }

    public void setJobUser_interviews(Set<JobUser_Interview> jobUser_interviews) {
        this.jobUser_interviews = jobUser_interviews;
    }
}

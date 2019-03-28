package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;

@Entity
public class JobUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String appStatus;

    private boolean matched;
    // if matched returns true, instantiate JUI obj

    @OneToOne(mappedBy = "jobUser")
    private JobUser_Interview jobUser_interview;


    // Constructor
    public JobUser(){

    }

    public JobUser(Job job, User user, String appStatus, boolean matched) {
        this.job = job;
        this.user = user;
        this.appStatus = appStatus;
        this.matched = matched;
    }

    // getter n setters
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

    public JobUser_Interview getJobUser_interview() {
        return jobUser_interview;
    }

    public void setJobUser_interview(JobUser_Interview jobUser_interview) {
        this.jobUser_interview = jobUser_interview;
    }
}

package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    @Lob // to set sql limit longer
    private String description;

    private Date datePosted;

    private boolean closed;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<JobUser> jobUsers;

    //consider join table
    @ManyToOne
    //@JoinColumn(name = "jobTitle_id")
    private JobTitle jobTitle;


    //=================================================
    //Constructors
    //=================================================
    //default

    public Job() {
    }

    //loaded
    public Job(String title, String description, Date datePosted, boolean closed, JobTitle jobTitle) {
        this.title = title;
        this.description = description;
        this.datePosted = datePosted;
        this.closed = closed;
        this.jobTitle = jobTitle;
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

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Collection<JobUser> getJobUsers() {
        return jobUsers;
    }

    public void setJobUsers(Collection<JobUser> jobUsers) {
        this.jobUsers = jobUsers;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }


}

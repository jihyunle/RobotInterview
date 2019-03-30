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


    @Lob // to set sql limit longer
    private String description;

    private String datePosted;

    private boolean closed;

    private String hiringManagerEmail;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<JobUser> jobUsers;

    //consider join table
    @ManyToOne
    //@JoinColumn(name = "jobTitle_id")
    private JobTitle jobTitle;

    //=================================================
    //Getters and Setters
    //=================================================
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getHiringManagerEmail()
    {
        return hiringManagerEmail;
    }

    public void setHiringManagerEmail(String hiringManagerEmail)
    {
        this.hiringManagerEmail = hiringManagerEmail;
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

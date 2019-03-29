package edu.montgomerycollege.drdoom.Models;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jobId;

   // private String jobTitle;

    private String jobDescription;

    private String jobDatePosted;

    private String jobClosed;

    @ManyToMany(mappedBy = "jobs", fetch = FetchType.LAZY)
    private Collection<User> users;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name="job_jobid"), inverseJoinColumns = @JoinColumn(name="keyword_kid"))
    private Collection<Keyword> keywords;




   @ManyToOne

   private JobTitle jobTitle;

    /*******************************************************/
    //Constructors


//public Job(){
//
//}
//
//    public Job(String jobDescription, String jobDatePosted, String jobClosed) {
//        this.jobDescription = jobDescription;
//        this.jobDatePosted = jobDatePosted;
//        this.jobClosed = jobClosed;
//    }

    /*******************************************************/
    //Getters and setters
    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }
//    public String getJobTitle() {

//        return jobTitle;
//    }

//    public void setJobTitle(String jobTitle) {
//        this.jobTitle = jobTitle;
//    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobDatePosted() {
        return jobDatePosted;
    }

    public void setJobDatePosted(String jobDatePosted) {
        this.jobDatePosted = jobDatePosted;
    }

    public String getJobClosed() {
        return jobClosed;
    }

    public void setJobClosed(String jobClosed) {
        this.jobClosed = jobClosed;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public Collection<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(Collection<Keyword> keywords) {
        this.keywords = keywords;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }
}

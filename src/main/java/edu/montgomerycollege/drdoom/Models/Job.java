package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jobId;

    private String jobTitle;

    @Lob // to set sql limit longer
    private String jobDescription;

    private Date jobDatePosted;

    private boolean jobClosed;

    private String jobStatus;

    @Email
    private String hiringManagerEmail;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<JobInterviewUser> jobInterviewUsers;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "jobInterviewUser_id")
//    private JobInterviewUser jobInterviewUser;

//    @ManyToMany(mappedBy = "jobs", fetch = FetchType.LAZY)
//    private Collection<User> users;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name="job_jobid"), inverseJoinColumns = @JoinColumn(name="keyword_kid"))
    private Collection<Keyword> keywords;


    /*******************************************************/
    //Constructors

    public Job() {
        this.jobClosed = false;
    }

    public Job(String jobTitle, String jobDescription, Date jobDatePosted, boolean jobClosed) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobDatePosted = jobDatePosted;
        this.jobClosed = jobClosed;
    }

    /*******************************************************/
    //Getters and setters
    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Date getJobDatePosted() {
        return jobDatePosted;
    }

    public void setJobDatePosted(Date jobDatePosted) {
        this.jobDatePosted = jobDatePosted;
    }

    public boolean isJobClosed() {
        return jobClosed;
    }

    public void setJobClosed(boolean jobClosed) {
        this.jobClosed = jobClosed;
    }

    public Set<JobInterviewUser> getJobInterviewUsers()
    {
        return jobInterviewUsers;
    }

    public void setJobInterviewUsers(Set<JobInterviewUser> jobInterviewUsers)
    {
        this.jobInterviewUsers = jobInterviewUsers;
    }

    //    public JobInterviewUser getJobInterviewUser() {
//        return jobInterviewUser;
//    }
//
//    public void setJobInterviewUser(JobInterviewUser jobInterviewUser) {
//        this.jobInterviewUser = jobInterviewUser;
//    }

//    public Collection<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Collection<User> users) {
//        this.users = users;
//    }

    public Collection<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(Collection<Keyword> keywords) {
        this.keywords = keywords;
    }

    public String getHiringManagerEmail()
    {
        return hiringManagerEmail;
    }

    public void setHiringManagerEmail(String hiringManagerEmail)
    {
        this.hiringManagerEmail = hiringManagerEmail;
    }

    public String getJobStatus()
    {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus)
    {
        this.jobStatus = jobStatus;
    }
}

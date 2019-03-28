package edu.montgomerycollege.drdoom.Models;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.Set;

@Entity
public class JobInterviewUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "interview_id")
    private Interview interview;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id")
    private Job job;


//    @OneToMany(mappedBy = "interviewId",
//            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<JobInterviewUser> jobInterviewUsers;

//    @OneToMany(mappedBy = "userId",
//            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<JobInterviewUser> jobInterviewUsers;

//    @OneToMany(mappedBy = "jobId",
//            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<JobInterviewUser> jobInterviewUsers;

    public JobInterviewUser() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Interview getInterview()
    {
        return interview;
    }

    public void setInterview(Interview interview)
    {
        this.interview = interview;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Job getJob()
    {
        return job;
    }

    public void setJob(Job job)
    {
        this.job = job;
    }

    //    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//
//    public Set<Interview> getInterviews() {
//        return interviews;
//    }
//
//    public void setInterviews(Set<Interview> interviews) {
//        this.interviews = interviews;
//    }
//
//    public Set<Job> getJobs() {
//        return jobs;
//    }
//
//    public void setJobs(Set<Job> jobs) {
//        this.jobs = jobs;
//    }
}

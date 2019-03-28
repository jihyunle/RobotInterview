package edu.montgomerycollege.drdoom.Models;
import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Models.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Entity
public class JobInterviewUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "jobInterviewUser",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<User> users;

    @OneToMany(mappedBy = "jobInterviewUser",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Interview> interviews;

    @OneToMany(mappedBy = "jobInterviewUser",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Job> jobs;

    public JobInterviewUser() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(Set<Interview> interviews) {
        this.interviews = interviews;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }
}
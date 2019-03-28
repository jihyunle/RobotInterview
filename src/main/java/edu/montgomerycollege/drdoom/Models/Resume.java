package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Resume
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Lob
    private String resumeData;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    private User user;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<JobInterviewUser> jobInterviewUsers;

//    @OneToOne(mappedBy = "resume")
//    private User user;

    //Getters and Setters
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getResumeData()
    {
        return resumeData;
    }

    public void setResumeData(String resumeData)
    {
        this.resumeData = resumeData;
    }

    public Set<JobInterviewUser> getJobInterviewUsers() {
        return jobInterviewUsers;
    }

    public void setJobInterviewUsers(Set<JobInterviewUser> jobInterviewUsers) {
        this.jobInterviewUsers = jobInterviewUsers;
    }

    //    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}

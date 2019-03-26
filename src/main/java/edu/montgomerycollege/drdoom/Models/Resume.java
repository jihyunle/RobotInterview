package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Resume
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    private String resumeData;

    @OneToOne(mappedBy = "resume")
    private User user;

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

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}

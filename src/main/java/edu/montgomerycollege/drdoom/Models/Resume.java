package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Lob
    private String resumeData;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    //=================================================
    //Constructors
    //=================================================
    //default
    public Resume() {

    }

    //loaded
    public Resume(@NotEmpty String resumeData) {
        this.resumeData = resumeData;
    }


    //=================================================
    //Getters and setters
    //=================================================


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResumeData() {
        return resumeData;
    }

    public void setResumeData(String resumeData) {
        this.resumeData = resumeData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

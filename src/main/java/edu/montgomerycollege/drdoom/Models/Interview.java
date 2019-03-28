package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long interviewId;

    private String interviewDate;

    private String interviewTime;

    // make this varchar as TEXT in MySQL
    private String chatHistory;

    @OneToMany(mappedBy = "id",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<JobInterviewUser> jobInterviewUsers;

    @OneToMany(mappedBy="interview", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Question> questions;

    public Interview(){

    }

    public long getInterviewId()
    {
        return interviewId;
    }

    public void setInterviewId(long interviewId)
    {
        this.interviewId = interviewId;
    }


    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(String chatHistory) {
        this.chatHistory = chatHistory;
    }

    public Set<JobInterviewUser> getJobInterviewUsers()
    {
        return jobInterviewUsers;
    }

    public void setJobInterviewUsers(Set<JobInterviewUser> jobInterviewUsers)
    {
        this.jobInterviewUsers = jobInterviewUsers;
    }


    public Collection<Question> getQuestions()
    {
        return questions;
    }

    public void setQuestions(Collection<Question> questions)
    {
        this.questions = questions;
    }
}

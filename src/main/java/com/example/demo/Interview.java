package com.example.demo;

import javax.persistence.*;

@Entity
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String interviewDate;

    private String interviewTime;

    // make this varchar as TEXT in MySQL
    private String chatHistory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jobInterviewUser_id")
    private JobInterviewUser jobInterviewUser;

    public Interview(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public JobInterviewUser getJobInterviewUser() {
        return jobInterviewUser;
    }

    public void setJobInterviewUser(JobInterviewUser jobInterviewUser) {
        this.jobInterviewUser = jobInterviewUser;
    }
}

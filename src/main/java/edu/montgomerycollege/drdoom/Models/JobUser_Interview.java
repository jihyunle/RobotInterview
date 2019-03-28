package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class JobUser_Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @OneToOne(cascade = CascadeType.ALL) // assuming one interview per job per user
//    @JoinColumn(name = "jui_id")

//    @OneToOne(mappedBy = "jobUser_interview")

    @ManyToOne
    private JobUser jobUser;

    private LocalDateTime interviewTime;

    private QuestionAnswer[] chatHistory;

    // Constructor

    public JobUser_Interview(){

    }

    public JobUser_Interview(JobUser jobUser, String interviewTime, QuestionAnswer[] chatHistory) {
        this.jobUser = jobUser;
        this.setInterviewTime(interviewTime);
        this.chatHistory = chatHistory;
    }


// Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public JobUser getJobUser() {
        return jobUser;
    }

    public void setJobUser(JobUser jobUser) {
        this.jobUser = jobUser;
    }

    public LocalDateTime getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm");
        this.interviewTime = LocalDateTime.parse(interviewTime, f);


    }

    public QuestionAnswer[] getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(QuestionAnswer[] chatHistory) {
        this.chatHistory = chatHistory;
    }
}

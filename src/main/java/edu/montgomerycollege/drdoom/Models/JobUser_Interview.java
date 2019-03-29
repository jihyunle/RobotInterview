package edu.montgomerycollege.drdoom.Models;

import org.springframework.core.annotation.Order;

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

    @OneToMany(mappedBy = "jobUser_interview", cascade = CascadeType.DETACH)
    @OrderColumn
    private QuestionAnswer[] chatHistory;





    // Constructor

    public JobUser_Interview(){
        setInterviewTime();

    }

    public JobUser_Interview(JobUser jobUser, QuestionAnswer[] chatHistory) {
        this.jobUser = jobUser;
        this.chatHistory = chatHistory;
        setInterviewTime();
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

    public void setInterviewTime() {

        this.interviewTime = LocalDateTime.now();
    }

    //    public String getInterviewTime() {
//        return interviewTime;
//    }
//
//    public void setInterviewTime(String interviewTime) {
////        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
////        this.interviewTime = LocalDateTime.parse(interviewTime, formatter);
//
//        this.interviewTime = interviewTime;
//    }

    public QuestionAnswer[] getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(QuestionAnswer[] chatHistory) {
        this.chatHistory = chatHistory;
    }
}
package edu.montgomerycollege.drdoom.Models;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jui_id")
    private JobUser_Interview jobUser_interview;

    private String question;

    private String answer;

    // constructor

    public QuestionAnswer(){

    }

    public QuestionAnswer(String question) {
        this.question = question;
    }



    // getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public JobUser_Interview getJobUser_interview() {
        return jobUser_interview;
    }

    public void setJobUser_interview(JobUser_Interview jobUser_interview) {
        this.jobUser_interview = jobUser_interview;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}

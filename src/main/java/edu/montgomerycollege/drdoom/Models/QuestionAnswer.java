package edu.montgomerycollege.drdoom.Models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String question;
    @Lob
    private String answer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jobUser_interview")
    private JobUser_Interview jobUser_interview;

    @ManyToMany(mappedBy = "questions", fetch = FetchType.LAZY) // might be @OneToMany
    private Collection<JobTitle> jobTitles;

    @OneToOne(mappedBy = "QuestionAnswer")
    private Answer answer_2;

    //=================================================
    //Constructors
    //=================================================
    //default
    public QuestionAnswer(){

    }

    //loaded

    public QuestionAnswer(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    //other
    public QuestionAnswer(String question) {
        this.question = question;
    }



    //=================================================
    //Getters and Setters
    //=================================================


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public JobUser_Interview getJobUser_interview() {
        return jobUser_interview;
    }

    public void setJobUser_interview(JobUser_Interview jobUser_interview) {
        this.jobUser_interview = jobUser_interview;
    }

    public Collection<JobTitle> getJobTitles()
    {
        return jobTitles;
    }

    public void setJobTitles(Collection<JobTitle> jobTitles)
    {
        this.jobTitles = jobTitles;
    }

    public Answer getAnswer_2() {
        return answer_2;
    }

    public void setAnswer_2(Answer answer_2) {
        this.answer_2 = answer_2;
    }
}
// my thought
//accept answer from user and save it in the database by creating table i already create a table
//then compare user or applicant answer with the real answer .real answer found in questionAnswer table
// so we will do like what we do for resume compare user answr with real answer if it
// contain some percent like 50% or any other percent the application status of the applicant will change to hired
//or other words else the application status change to rejected.

package edu.montgomerycollege.drdoom.Models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@Entity
public class JobTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String job_title;

    @OneToMany(mappedBy = "jobTitle") //removed cascadeType=ALL
    private Set<Job> jobs;

    @ManyToMany(fetch = FetchType.LAZY) //removed cascadeType=ALL
    @JoinTable(joinColumns = @JoinColumn(name="keyword_id"), inverseJoinColumns = @JoinColumn(name="id"))
    private Collection<Keyword> keywords;

//    @ManyToOne
//    @JoinColumn(name = "Question_id")
//    private QuestionAnswer questionAnswer;
//
//    public QuestionAnswer getQuestionAnswer() {
//        return questionAnswer;
//    }
//
//    public void setQuestionAnswer(QuestionAnswer questionAnswer) {
//        this.questionAnswer = questionAnswer;
//    }

        @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name="jobTitle_id"),
            inverseJoinColumns = @JoinColumn(name="questionAnswer_id"))
    private Collection<QuestionAnswer> questions;


    //=================================================
    //Constructors
    //=================================================
    //default
    public JobTitle() {
    }

    //loaded
    public JobTitle(@NotNull String job_title) {
        this.job_title = job_title;
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

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public Collection<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(Collection<Keyword> keywords) {
        this.keywords = keywords;
    }

    public Collection<QuestionAnswer> getQuestions()
    {
        return questions;
    }

    public void setQuestions(Collection<QuestionAnswer> questions)
    {
        this.questions = questions;
    }
}

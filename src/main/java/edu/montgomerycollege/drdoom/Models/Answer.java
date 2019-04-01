package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Lob
    private String answer;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name="answer_id"),
            inverseJoinColumns = @JoinColumn(name="questionAnswer_id"))
    private Collection<QuestionAnswer> questions;


    public Answer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Collection<QuestionAnswer> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<QuestionAnswer> questions) {
        this.questions = questions;
    }
}

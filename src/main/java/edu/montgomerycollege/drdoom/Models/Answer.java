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

    public Collection<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(Collection<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    @OneToMany(mappedBy = "answer")
    private Collection<QuestionAnswer> questionAnswers;


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


}

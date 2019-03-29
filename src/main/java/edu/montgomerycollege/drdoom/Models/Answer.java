package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;

@Entity
public class Answer
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String answerText;

//    @OneToOne(mappedBy = "answer")
//    private Question question;

    public Answer(String answerText)
    {
        this.answerText = answerText;
    }

    public Answer()
    {
    }

//    public Answer(String answerText, Question question)
//    {
//        this.answerText = answerText;
//        this.question = question;
//    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getAnswerText()
    {
        return answerText;
    }

    public void setAnswerText(String answerText)
    {
        this.answerText = answerText;
    }

//    public Question getQuestion()
//    {
//        return question;
//    }
//
//    public void setQuestion(Question question)
//    {
//        this.question = question;
//    }
}

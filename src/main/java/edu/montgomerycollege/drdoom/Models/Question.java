package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;

@Entity
public class Question
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String questionText;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="answer_id")
    private Answer answer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="interview_id")
    private Interview interview;


    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getQuestionText()
    {
        return questionText;
    }

    public void setQuestionText(String questionText)
    {
        this.questionText = questionText;
    }

    public Answer getAnswer()
    {
        return answer;
    }

    public void setAnswer(Answer answer)
    {
        this.answer = answer;
    }

    public Interview getInterview()
    {
        return interview;
    }

    public void setInterview(Interview interview)
    {
        this.interview = interview;
    }
}

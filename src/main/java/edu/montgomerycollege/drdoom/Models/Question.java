package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;

@Entity
public class Question
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String questionText;

    private String answerText;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="answer_id")
//    private Answer answer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="interview_id")
    private Interview interview;

    public Question()
    {
    }

//    public Question(String questionText, Answer answer)
//    {
//        this.questionText = questionText;
//        this.answer = answer;
//    }
//
//    public Question(String questionText, Answer answer, Interview interview)
//    {
//        this.questionText = questionText;
//        this.answer = answer;
//        this.interview = interview;
//    }


    public Question(String questionText, String answerText)
    {
        this.questionText = questionText;
        this.answerText = answerText;
    }

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

    public String getAnswerText()
    {
        return answerText;
    }

    public void setAnswerText(String answerText)
    {
        this.answerText = answerText;
    }

    //    public Answer getAnswer()
//    {
//        return answer;
//    }
//
//    public void setAnswer(Answer answer)
//    {
//        this.answer = answer;
//    }

    public Interview getInterview()
    {
        return interview;
    }

    public void setInterview(Interview interview)
    {
        this.interview = interview;
    }


}

package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.*;
import edu.montgomerycollege.drdoom.Repositories.InterviewRepository;
import edu.montgomerycollege.drdoom.Repositories.JobInterviewUserRepository;
import edu.montgomerycollege.drdoom.Repositories.JobRepository;
import edu.montgomerycollege.drdoom.Repositories.QuestionRepository;
import edu.montgomerycollege.drdoom.Services.EmailServiceImpl;
import edu.montgomerycollege.drdoom.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ws.mime.Attachment;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Email;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Controller
public class JesseController
{
    @Autowired
    JobRepository jobRepository;

    @Autowired
    EmailServiceImpl email;

    @Autowired
    private UserService userService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    JobInterviewUserRepository jobInterviewUserRepository;




    @GetMapping({"/interview/{id}"})
    public String interviewGet(@PathVariable("id") long id, Model model)
    {
        Job jobObject = jobRepository.findById(id).get();
        model.addAttribute("job", jobObject);

        //find associated JIU
        JobInterviewUser jobInterviewUser = jobInterviewUserRepository.findByUserAndJob(userService.getUser(),
                                                                                        jobObject);
        Interview interview = new Interview();
        //Get (random selection of) questions (that match correctly)
            //to do random, get random number between 1 and length, then that many random numbers (non-repeating)
        // between 1 and length, those questions are selected
        Iterable<Question> questions = questionRepository.findAll();
        Collection<Question> questions1 = new ArrayList<Question>(Arrays.asList());
        for (Question question:questions)
        {
            questions1.add(question);
        }
        interview.setQuestions(questions1);

        jobInterviewUser.setInterview(interview);
        jobInterviewUserRepository.save(jobInterviewUser);
        model.addAttribute("jiu", jobInterviewUser);
       // System.out.println(jobInterviewUser.getInterview().getQuestions());
        return "interview";
    }

    @PostMapping("/interview")
//    public String interviewPost(@ModelAttribute("interview") Interview interview,
//                                @ModelAttribute("job") Job job,
//                                @RequestParam("answers") String[] answers)
    public String interviewPost(@ModelAttribute("jiu")JobInterviewUser jiu,
                                //@RequestParam("questions") String[] questions,
                                @RequestParam("answers") String[] answers)
    {
        //because only the id field is in the form, only that is filled in when the object returns.  Everything else
        // is null
        JobInterviewUser jobInterviewUserObject = jobInterviewUserRepository.findById(jiu.getId()).get();
        Interview interviewObject = jobInterviewUserObject.getInterview();
        Iterable<Question> iterquestion = questionRepository.findAll();

        int i = 0;
        String data = "";
        for (Question question : iterquestion)
        {
            data+="Question: " + question.getQuestionText() + " Answer: " + answers[i] +"\n";
            question.setQuestionText(question.getQuestionText());
            question.setAnswerText(answers[i]);
            i++;
        }
        //set full chat history
        interviewObject.setChatHistory(data);
        //save changes
        jobInterviewUserRepository.save(jobInterviewUserObject);

        Collection<Question> finishedQuestions = new ArrayList<Question>(i);

        jobInterviewUserObject.getInterview().setQuestions(finishedQuestions);

        //interviewObject.setQuestions(finishedQuestions);

        // System.out.println(jiu.getInterview().getQuestions().size());

        //get list of questions
        //Collection<Question> questions = jobInterviewUserObject.getInterview().getQuestions();
        //System.out.println(questions.toString());
//        for (Question question:temp)
//        {
//            question.setAnswerText(answer[i]);
//            i++;
//        }
        //interview.setQuestions(temp);
        try
        {
            sendEmailWithAttachment(interviewObject.getChatHistory(), jobInterviewUserObject);
        }catch (IOException e)
        {
            //don't send if there's an io exception
            System.out.println(e);
        }


        return "interviewComplete";
    }


    @RequestMapping("/sendemail")
    public String sendEmailWithOutTemplating(String attach) throws UnsupportedEncodingException
    {

        //email.send("whatever@whatever.com", "", "Subject", "body",);
        return "index";
    }

    public void sendEmailWithAttachment(String attach, JobInterviewUser jobInterviewUserObject) throws UnsupportedEncodingException,
                                                                                 IOException
    {

        Files.write(Paths.get("testFile.txt"),
                    attach.getBytes());

        File file = new File("testFile.txt");
        String toEmail = jobInterviewUserObject.getJob().getHiringManagerEmail();
        if(toEmail==null)
        {
            toEmail = "jesseberliner@gmail.com";
        }


        email.send("whatever@whatever.com", toEmail , "Subject", "body", file);
    }

}

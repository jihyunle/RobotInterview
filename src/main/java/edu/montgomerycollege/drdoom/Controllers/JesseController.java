package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.*;
import edu.montgomerycollege.drdoom.Repositories.JobRepository;
import edu.montgomerycollege.drdoom.Repositories.JobUserRepository;
import edu.montgomerycollege.drdoom.Repositories.JobUser_InterviewRepository;
import edu.montgomerycollege.drdoom.Repositories.QuestionAnswerRepository;
import edu.montgomerycollege.drdoom.Services.EmailServiceImpl;
import edu.montgomerycollege.drdoom.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    JobUserRepository jobUserRepository;

    @Autowired
    JobUser_InterviewRepository juiRepository;

    @Autowired
    QuestionAnswerRepository questionAnswerRepository;


    @GetMapping({"/interview/{id}"})
    public String interviewGet(@PathVariable("id") long jobUserId, Model model)
    {
        //get jobUser
        JobUser jobUser = jobUserRepository.findById(jobUserId).get();
        //create a new instance of JobUser_Interview
        JobUser_Interview jobUser_interview = juiRepository.findByJobUser(jobUser);

        //set jobUserIntervew's jobUser
        //jobUser_interview.setJobUser(jobUser);


        //Get (random) selection of questions (that match correctly)
        Iterable<QuestionAnswer> questions = questionAnswerRepository.findAll();
        Iterator<QuestionAnswer> iter = questions.iterator();
        Collection<QuestionAnswer> copy = new ArrayList<QuestionAnswer>();
        while (iter.hasNext())
        {
            copy.add(iter.next());
        }

        jobUser_interview.setChatHistory(copy); //This saves it, but can't get the object on the other side

        //save jui
        juiRepository.save(jobUser_interview);

        //add jobUser_interview to model
        model.addAttribute("jui", jobUser_interview);
        //add questions to model
        model.addAttribute("questions", questions);

        return "interview";
    }

    @PostMapping("/interview")
    public String processInterview(@ModelAttribute("jui") JobUser_Interview jobUser_interview,
                                   @RequestParam("answers") String[] answers,
                                   Model model)
    {

        JobUser_Interview temp = juiRepository.findById(jobUser_interview.getId()).get();
        //chatHistory is currently Null
        //iterate through questions and answers, saving the answer to the object
        Iterable<QuestionAnswer> questions = questionAnswerRepository.findAll();
        Iterator<QuestionAnswer> iter = questions.iterator();
        Collection<QuestionAnswer> copy = new ArrayList<QuestionAnswer>();
        int i = 0;
        while (iter.hasNext())
        {
            QuestionAnswer tempqa = iter.next();
            tempqa.setAnswer(answers[i]);
            i++;
            questionAnswerRepository.save(tempqa);
            copy.add(tempqa);
        }
        jobUser_interview.setChatHistory(copy);
        juiRepository.save(jobUser_interview);


        model.addAttribute("jui", jobUser_interview);
        try
        {
            String qaText = getStringVal(jobUser_interview.getChatHistory());
            sendEmailWithAttachment(qaText, jobUser_interview);
        }
        catch (IOException e)
        {
            //don't send if there's an io exception
            System.out.println(e);
        }
        return "interviewHistory";
    }


    public void sendEmailWithAttachment(String attach, JobUser_Interview jobUser_interview) throws UnsupportedEncodingException,
                                                                                                   IOException
    {

        Files.write(Paths.get("testFile.txt"),
                    attach.getBytes());

        File file = new File("testFile.txt");
        //String toEmail = jobUser_interview.getJobUser().getJob().getHiringManagerEmail();
        //Job job = jobUser_interview.getJobUser().getJob();
        String toEmail = "jesseberliner@hotmail.com";
        if (toEmail == null)
        {
            toEmail = "jesseberliner@gmail.com";
        }


        email.send("whatever@whatever.com", toEmail, "Subject", "body", file);
    }

    public String getStringVal(Collection<QuestionAnswer> collection)
    {
        String fullText = "";
        for (QuestionAnswer qa : collection)
        {
            fullText+=qa.getQuestion() + "\n" + qa.getAnswer() +"\n";
        }
        return fullText;
    }
}



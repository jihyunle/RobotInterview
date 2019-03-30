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
    public String interviewGet(@PathVariable("id") long id, Model model)
    {
        //get jobUser
        JobUser jobUser = jobUserRepository.findById(id).get();
        System.out.println(jobUser.getId());
        //create a new instance of JobUser_Interview
        JobUser_Interview jobUserInterview = new JobUser_Interview();
        //set jobUserIntervew's jobUser
        jobUserInterview.setJobUser(jobUser);

        //set jobUserInterview's chatHistory--does this need to be done?


        //Get (random) selection of questions (that match correctly)
        Iterable<QuestionAnswer> questions = questionAnswerRepository.findAll();

        Iterator<QuestionAnswer> iter = questions.iterator();
        Collection<QuestionAnswer> copy = new ArrayList<QuestionAnswer>();
        while (iter.hasNext())
        {
            copy.add(iter.next());
        }

        jobUserInterview.setChatHistory(copy);

        //saves jobUser, propagates change to jobUser_Interview
        jobUserRepository.save(jobUser);
        //juiRepository.save(jobUserInterview);

        //add questions to model
        model.addAttribute("questions", questions);
        model.addAttribute("jui", jobUserInterview);


        return "interview";
    }

    @PostMapping("/interview")
    public String processInterview(@ModelAttribute("jui")JobUser_Interview jobUser_interview,
                                   @RequestParam("answers")String[] answers,
                                   Model model){

        if(jobUser_interview==null)
        {
            System.out.println("null");

        }
        System.out.println(jobUser_interview.getId());
       // jui = juiRepository.findById(id).get();

        Collection<QuestionAnswer> chatHistory = jobUser_interview.getChatHistory();

        jobUser_interview.setChatHistory(chatHistory);

        // resave jui obj
        juiRepository.save(jobUser_interview);

//        for(QuestionAnswer qa: jui.getChatHistory()){
//            System.out.println(qa.getQuestion());
//        }
//        System.out.println("question answer.....");
        // now we have ans associated with each question
        model.addAttribute("jui", jobUser_interview);
        try
        {
            sendEmailWithAttachment("Testing", jobUser_interview);
        }catch (IOException e)
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
        String toEmail = "jesseberliner@gmail.com";
        if(toEmail==null)
        {
            toEmail = "jesseberliner@gmail.com";
        }


        //email.send("whatever@whatever.com", toEmail , "Subject", "body", file);
    }

}

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

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

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

        //create a new instance of JobUser_Interview
        JobUser_Interview jobUserInterview = new JobUser_Interview();
        //set jobUserIntervew's jobUser
        jobUserInterview.setJobUser(jobUser);
        //set jobUserInterview's chatHistory


        //Get (random) selection of questions (that match correctly)//NOT WORKING-CAN RETURN ALL QUESTIONS, BUT CANNOT initialize CHAT HISTORY
        Iterable<QuestionAnswer> questions = questionAnswerRepository.findAll();
//
//        jobUserInterview.setChatHistory(qaList);

        //saves jobUser, propagates change to jobUser_Interview
        jobUserRepository.save(jobUser);

        //add questions to model
        model.addAttribute("questions", questions);
//        model.addAttribute("questions", qaList);  //this line
        model.addAttribute("jui", jobUserInterview);
//




        return "interview";
    }

    @PostMapping("/interview")
    public String processInterview(@ModelAttribute("jui") JobUser_Interview jui,
                                   @RequestParam("answers")String[] answers,
                                   @RequestParam("juiID") long id,
                                   Model model){

        jui = juiRepository.findById(id).get();

        Set<QuestionAnswer> chatHistory = jui.getChatHistory();



        jui.setChatHistory(chatHistory);

        // resave jui obj
        juiRepository.save(jui);

//        for(QuestionAnswer qa: jui.getChatHistory()){
//            System.out.println(qa.getQuestion());
//        }
//        System.out.println("question answer.....");
        // now we have ans associated with each question
        model.addAttribute("jui", jui);
        return "interviewHistory";
    }


    @RequestMapping("/sendemail")
    public String sendEmailWithOutTemplating() throws UnsupportedEncodingException
    {
        String from = userService.getUser().getEmail();


        email.send(from, "jesseberliner@hotmail.com", "Subject", "body");
        return "index";
    }


}

package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Models.JobUser;
import edu.montgomerycollege.drdoom.Models.JobUser_Interview;
import edu.montgomerycollege.drdoom.Models.QuestionAnswer;
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
    QuestionAnswerRepository qaRepository;


    @GetMapping({"/interview/{id}"})
    public String interviewGet(@PathVariable("id") long id, Model model)
    {
        JobUser jobUser = jobUserRepository.findById(id).get();
        JobUser_Interview jui = juiRepository.findByJobUser(jobUser);

        model.addAttribute("jui", jui);


//        Job jobObject = jobRepository.findById(id).get();
//        model.addAttribute("job", jobObject);
//        //Get random selection of questions that match correctly
//        Iterable<Question> questions = questionRepository.findAll();
//        model.addAttribute("questions", questions);


        return "interview";
    }

    @PostMapping("/interview")
    public String processInterview(@ModelAttribute("jui") JobUser_Interview jui,
                                   @RequestParam("answers")String[] answers,
                                   @RequestParam("juiID") long id,
                                   Model model){

        jui = juiRepository.findById(id).get();

        QuestionAnswer[] chatHistory = jui.getChatHistory();

        for (int i=0; i<chatHistory.length; i++){
            chatHistory[i].setAnswer(answers[i]);
            QuestionAnswer qa =
                    qaRepository.findById(chatHistory[i].getId()).get();
            qaRepository.save(qa);
        }

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

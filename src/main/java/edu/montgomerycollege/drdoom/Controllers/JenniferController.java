package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.*;
import edu.montgomerycollege.drdoom.Repositories.JobUserRepository;
import edu.montgomerycollege.drdoom.Repositories.QuestionAnswerRepository;
import edu.montgomerycollege.drdoom.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class JenniferController {

    @Autowired
    JobUserRepository jobUserRepository;

    @Autowired
    QuestionAnswerRepository qaRepository;

    @Autowired
    UserService userService;

    private User user;


    @RequestMapping("/appeal")
    public String appeal(@PathVariable("id") long id, Model model){
        return "appeal";
    }

    @RequestMapping("/myinterviews")
    public String showInterviews(Model model){
        Iterable<JobUser> jobs = jobUserRepository.findAllByUser(user);

        model.addAttribute("jobs", jobs);
        model.addAttribute(user); // may not be needed
        return "myinterviews";

    }




}

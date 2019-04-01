package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.*;
import edu.montgomerycollege.drdoom.Repositories.JobUserRepository;
import edu.montgomerycollege.drdoom.Repositories.QuestionAnswerRepository;
import edu.montgomerycollege.drdoom.Repositories.ResumeRepository;
import edu.montgomerycollege.drdoom.Repositories.UserRepository;
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

    private User user;

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;


    @GetMapping("/resumeupload")
    public String resumeUpload(Model model){
//        model.addAttribute("id", id);
        model.addAttribute("resume", new Resume());
        model.addAttribute("resumes", resumeRepository.findAll());
        return "resumeForm";
    }

    @PostMapping("/resumeupload")
    public String processResume(@ModelAttribute("resume") Resume resume,
                                BindingResult result
//                                @PathVariable("resumeID") long id
    )
    {

        if (result.hasErrors()){
            return "resumeForm";
        }
        // get user
        user = userService.getUser();
        // set user of that resume obj
        resume.setUser(user);

//        resume.setUser(userRepository.findById(id).get());
        resumeRepository.save(resume);

        return "confirmResume";


    }


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

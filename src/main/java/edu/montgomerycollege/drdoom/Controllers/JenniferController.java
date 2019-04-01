package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.*;
import edu.montgomerycollege.drdoom.Repositories.*;
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
    UserService userService;

    @Autowired
    JobUser_InterviewRepository juiRepository;

    @GetMapping("/resumeupload")
    public String resumeUpload(Model model){
//        model.addAttribute("id", id);
        model.addAttribute("resume", new Resume());
        model.addAttribute("resumes", resumeRepository.findAll());
        return "resumeForm";
    }

    @PostMapping("/resumeupload")
    public String processResume(@ModelAttribute("resume") Resume resume,
                                @ModelAttribute("user")User user,
                                BindingResult result
//                                @PathVariable("resumeID") long id
    )
    {

//        System.out.println(resume.getId());


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


    @GetMapping("/appeal")
    public String appealForm(Model model){
        model.addAttribute("jui", new JobUser_Interview());
        return "appeal";
    }

    @PostMapping("/appeal")
    public String processAppeal(@ModelAttribute("jui") JobUser_Interview jobUser_interview){
        juiRepository.save(jobUser_interview);
        return "redirect:/";
    }

    @RequestMapping("/myinterviews")
    public String showInterviews(Model model){
        Iterable<JobUser> jobs = jobUserRepository.findAllByUser(user);

        model.addAttribute("jobs", jobs);
        model.addAttribute(user); // may not be needed
        return "myinterviews";

    }

    @RequestMapping("/about")
    public String aboutAuthors(){
        return "about";
    }


}

package edu.montgomerycollege.drdoom.Controllers;



import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Models.JobInterviewUser;
import edu.montgomerycollege.drdoom.Models.Resume;
import edu.montgomerycollege.drdoom.Models.User;
import edu.montgomerycollege.drdoom.Repositories.JobInterviewUserRepository;
import edu.montgomerycollege.drdoom.Repositories.JobRepository;
import edu.montgomerycollege.drdoom.Repositories.UserRepository;
import edu.montgomerycollege.drdoom.Services.CustomUserDetails;
import edu.montgomerycollege.drdoom.Services.ParseResume;
import edu.montgomerycollege.drdoom.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Controller
public class MainController
{
    @Autowired
    private UserService userService;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ParseResume parser;

    @Autowired
    JobInterviewUserRepository jobInterviewUserRepository;



    @RequestMapping({"/index","/"})
    public String welcomePage()
    {
        return "index";
    }

    @RequestMapping({"/jobs"})
    public String allJobs(Model model)
    {
        model.addAttribute("jobs", jobRepository.findAll());
        return "jobs";
    }

    @RequestMapping({"/myjobs"})
    public String myJobs(Model model)
    {
        model.addAttribute("jobs", jobRepository.findAll());

        //get userId
        User user = userService.getUser();
        //Get JIUs associated with this user
//        Set<User> users = jobInterviewUserRepository.findAllByUser(user);
//        System.out.println(users.toString());
        //get set of jobs for this user
//        Collection<Job> jobs = jobRepository


        return "myjobs";
    }

    @GetMapping({"/apply/{id}"})
    public String apply(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("job", jobRepository.findById(id).get());
        model.addAttribute("job_id", id);
        model.addAttribute("resume", new Resume());
        return "apply";
    }

    @PostMapping({"/apply"})
    public String applied(@ModelAttribute("resume")Resume resume, BindingResult resultA,
                          @ModelAttribute("job") Job job, BindingResult resultB,
                          Model model)
        {
        //add Job to my jobs collection
        User user = userService.getUser();
        //JobInterviewUser jobInterviewUser = jobReposit
        //JobInterviewUser user2 = user.getJobInterviewUsers();
            System.out.println(user.getJobInterviewUsers());
        Long userId =  user.getUserId();
        //job.getJobId();
        Job jobObject = jobRepository.findById(job.getJobId()).get();
        //Set<Job> jobs = user2.getJobs();
       // jobs.add(jobObject);
        //user2.setJobs(jobs);

        //add Resume to user

        //user.set

        //save user
        //userRepository.save(user);


       // model.addAttribute("job", jobObject);
       // model.addAttribute("resume", resume);
        return "applied";
    }






}

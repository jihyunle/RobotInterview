package edu.montgomerycollege.drdoom.Controllers;



import edu.montgomerycollege.drdoom.Models.*;
//import edu.montgomerycollege.drdoom.Models.JobInterviewUser;
import edu.montgomerycollege.drdoom.Repositories.*;
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

//    @Autowired
//    JobInterviewUserRepository jobInterviewUserRepository;

    @Autowired
    JobUserRepository jobUserRepository;

    @Autowired
    ResumeRepository resumeRepository;


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
        model.addAttribute("jobs", jobUserRepository.findAllByUser(userService.getUser()));

        //get userId
        //Get JIUs associated with this user
        //get set of jobs for this user


        return "myjobs";
    }

    @GetMapping({"/apply/{id}"})
    public String apply(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("job", jobRepository.findById(id).get());
        model.addAttribute("id", id);
        model.addAttribute("resume", new Resume());
        return "apply";
    }

    @PostMapping({"/apply"})
    public String applied(@ModelAttribute("resume")Resume resume, BindingResult resultA,
                          @ModelAttribute("job") Job job, BindingResult resultB,
                          @RequestParam("jobId") long id,
                          Model model) {
        // resumeRepository.save(resume);

        // get user
        User user = userService.getUser();
        // add the resume to that user
        user.getResumes().add(resume);
        // re-save user
        userRepository.save(user);

        // find job by its id
        job = jobRepository.findById(id).get();
        // create a new jobUser obj
        JobUser jobUser = new JobUser();
        // save the job and user to the new obj
        jobUser.setJob(job);
        jobUser.setUser(user);
        jobUser.setMatched(true);
        jobUser.setAppStatus("pending interview");
        // save the obj to the repository
        jobUserRepository.save(jobUser);

        // if user's resume for that job matches, create a jui obj
        if(jobUser.isMatched()){
            // creating obj
            JobUser_Interview jui = new JobUser_Interview();
            jui.setJobUser(jobUser);
            // assigning chatHistory field
//            QuestionAnswer[] chatHistory = new QuestionAnswer[];
//            for (int i=0; i<chatHistory.length; i++){
//
//            }
//            jui.setChatHistory(chatHistory);
        }

        model.addAttribute("jobUser", jobUser);




        //JobInterviewUser jobInterviewUser = jobReposit
        //JobInterviewUser user2 = user.getJobInterviewUsers();
//            System.out.println(user.getJobInterviewUsers());
        Long userId =  user.getId();
        //job.getJobId();
//        Job jobObject = jobRepository.findById(job.getId();
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

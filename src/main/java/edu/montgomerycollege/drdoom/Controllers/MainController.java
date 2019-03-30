package edu.montgomerycollege.drdoom.Controllers;


//import edu.montgomerycollege.drdoom.Models.JobInterviewUser;
import edu.montgomerycollege.drdoom.Repositories.*;
import edu.montgomerycollege.drdoom.Models.*;

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
    JobTitleRepository jobTitleRepository;

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
    public String allJobs(@ModelAttribute Job job, Model model)
    {
        model.addAttribute("jobs", jobRepository.findAll());
        model.addAttribute("jobTitle",jobTitleRepository.findAll()); //This should be changed to only get non-deleted ones once Job model is changed
        return "jobs";
    }

    @RequestMapping({"/myjobs"})
    public String myJobs(Model model)
    {
        model.addAttribute("jobUsers", jobUserRepository.findAllByUser(userService.getUser()));

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

        // get user
        User user = userService.getUser();
        // set userid again
        resume.setUser(user);

        // add the resume to that user
        user.getResumes().add(resume);
        // save user
        userRepository.save(user);
        // find job by its id
        job = jobRepository.findById(id).get();
        // create a new jobUser obj
        JobUser jobUser = new JobUser();
        // save the job and user to the new obj
        jobUser.setJob(job);
        jobUser.setUser(user);

//Temporarily commented out to make all applications match, remove Boolean matches = true;
        //parse resume and see if it matches 80% of keywords
        //Boolean matches = ParseResume.parseResume(resume, job.getJobTitle());
        Boolean matches = true;

        // if user's resume for that job matches, create a jui obj and save additional info in jobUser
        if (matches) {
            // creating obj
            JobUser_Interview jui = new JobUser_Interview();
            jui.setJobUser(jobUser);
            //save additional info to jobUser
            jobUser.setMatched(true); //this should only be true if matches, else it stays false
            jobUser.setAppStatus("pending interview date"); //this should only be set if matches, else it stays blank

            // assigning chatHistory field
//            QuestionAnswer[] chatHistory = new QuestionAnswer[];
//            for (int i=0; i<chatHistory.length; i++){
//
//            }
//            jui.setChatHistory(chatHistory);
        }





        //make all needed saves
        // re-save user
        userRepository.save(user);
        // save the obj to the repository
        jobUserRepository.save(jobUser);

        userRepository.save(user);


        //add to model
        model.addAttribute("jobUser", jobUser);
        model.addAttribute("job", job);
        model.addAttribute("matches", matches);

        //final return page is chosen dynamically based on pass or fail of parser
        return "applied";
    }
}

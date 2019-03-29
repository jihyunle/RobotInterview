package edu.montgomerycollege.drdoom.Controllers;


import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Models.JobTitle;
import edu.montgomerycollege.drdoom.Models.Resume;
import edu.montgomerycollege.drdoom.Models.User;
import edu.montgomerycollege.drdoom.Repositories.JobRepository;
import edu.montgomerycollege.drdoom.Repositories.JobTitleRepository;
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
import java.util.Collection;
import java.util.Date;
import java.util.Map;

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


    @RequestMapping({"/index","/"})
    public String welcomePage()
    {
        return "index";
    }

    @RequestMapping({"/jobs"})
    public String allJobs(@ModelAttribute Job job, Model model)
    {
        model.addAttribute("jobs", jobRepository.findAll());
        model.addAttribute("jobTitle",jobTitleRepository.findAll());
        return "jobs";
    }

    @RequestMapping({"/myjobs"})
    public String myJobs(Model model)
    {
        model.addAttribute("jobs", userService.getUser().getJobs());
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
    public String applied(@ModelAttribute("resume")Resume resume, @ModelAttribute("jobTitle") JobTitle jobTitle,
                          BindingResult resultA,
                          @ModelAttribute("job")Job job, BindingResult resultB,
                          Model model)
        {
        //add Job to my jobs collection
        User user = userService.getUser();
        Job jobObject = jobRepository.findById(job.getJobId()).get();
        Collection<Job> jobs = user.getJobs();
        jobs.add(jobObject);
        user.setJobs(jobs);

        //add Resume to user
        user.setResume(resume);


        //save user
        userRepository.save(user);
        //parse resume and see if it matches 80% of keywords
              Boolean bool=ParseResume.parseResume(resume,jobObject.getJobTitle());
            model.addAttribute("bool",bool);
            if(bool){
                System.out.println("true");
            }
            else {
                System.out.println("false");
            }
        model.addAttribute("job", jobObject);
        return "applied";
    }
}

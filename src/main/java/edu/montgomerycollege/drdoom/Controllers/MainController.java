package edu.montgomerycollege.drdoom.Controllers;



import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Models.JobInterviewUser;
import edu.montgomerycollege.drdoom.Models.Resume;
import edu.montgomerycollege.drdoom.Models.User;
import edu.montgomerycollege.drdoom.Repositories.InterviewRepository;
import edu.montgomerycollege.drdoom.Repositories.JobInterviewUserRepository;
import edu.montgomerycollege.drdoom.Repositories.JobRepository;
import edu.montgomerycollege.drdoom.Repositories.UserRepository;
import edu.montgomerycollege.drdoom.Services.ParseResume;
import edu.montgomerycollege.drdoom.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class MainController
{
    @Autowired
    private UserService userService;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    InterviewRepository interviewRepository;

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

        //get user
        User user = userService.getUser();
        //get jiu's associated with that user
        Iterable<JobInterviewUser> jIUs = jobInterviewUserRepository.findAllByUser(user);
        //create a collection to hold job objects
        Collection<Job> jobs=new ArrayList<Job>();
        //create collection to hold jius
        Collection<String> jiusStatus= new ArrayList<String>();
        //iterate through jiu's, adding jobs
        for(JobInterviewUser user1: jIUs)
        {
                jobs.add(user1.getJob());
                jiusStatus.add(user1.getStatus());
        }
        model.addAttribute("jobs", jobs);
        //model.addAttribute("jius", jius);
        model.addAttribute("status", jiusStatus);
        System.out.println(jiusStatus.size());

        return "myjobs2";
    }

    @GetMapping({"/apply/{id}"})
    public String apply(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("job", jobRepository.findById(id).get());
        JobInterviewUser jiu = jobInterviewUserRepository.findByUserAndJob(userService.getUser(),
                                                                     jobRepository.findById(id).get());
        if(jiu!=null )
        {
            model.addAttribute("error", "You've already applied to this position");
            return "error";
        }
//        model.addAttribute("job_id", id);
        model.addAttribute("resume", new Resume());
        return "apply";
    }

    @PostMapping({"/apply"})
    public String applied(@ModelAttribute("resume")Resume resume, BindingResult resultA,
                          @ModelAttribute("job") Job job, BindingResult resultB,
                          Model model)
        {
            Iterable<JobInterviewUser> jobInterviewUsers =
                    jobInterviewUserRepository.findAllByUser(userService.getUser());

            Job jobObject = jobRepository.findById(job.getJobId()).get();

            JobInterviewUser newUserJob = new JobInterviewUser();
            newUserJob.setJob(jobObject);
            newUserJob.setUser(userService.getUser());

            if(parser.parseResume(resume, jobObject))
            {
                newUserJob.setStatus("interview");
            }
            else
            {
                newUserJob.setStatus("closed");
            }
            jobInterviewUserRepository.save(newUserJob);


            model.addAttribute("job", jobObject);
            model.addAttribute("resume", resume);
            return "applied";
    }






}

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

        Iterable<JobInterviewUser> jIUs = jobInterviewUserRepository.findAllByUser(user);
        Collection<Job> jobs=new ArrayList<Job>();
        for(JobInterviewUser user1: jIUs){
                jobs.add(user1.getJob());
            //System.out.println(jobs.size());
        }
        model.addAttribute("jobs", jobs);


        //System.out.println(temp);
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
            jobInterviewUserRepository.save(newUserJob);





        model.addAttribute("job", jobObject);
        model.addAttribute("resume", resume);
        return "applied";
    }






}

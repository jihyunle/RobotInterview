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
import java.time.LocalDateTime;
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

    @Autowired
    JobUser_InterviewRepository juiRepository;

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
        //get current user
        User current_user = userService.getUser();

        //initialize collection of JobUser_Interview objects
        Collection<JobUser_Interview> jobUser_interviews = new ArrayList<JobUser_Interview>();
        //iterate through all jobUsers, adding their jui to jobUser_interviews
        Iterable<JobUser> jobUsers = jobUserRepository.findAllByUser(userService.getUser());
        Iterator<JobUser> iterator = jobUsers.iterator();
        while(iterator.hasNext())
        {
            jobUser_interviews.add(juiRepository.findByJobUser(iterator.next()));
        }

        model.addAttribute("now", new Date());
        model.addAttribute("juis", jobUser_interviews);
        return "myjobs";
    }

    @GetMapping({"/apply/{id}"})
    public String apply(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("job", jobRepository.findById(id).get());
        model.addAttribute("id", id);
        model.addAttribute("resume", new Resume());
        // added this line to retrieve all resumes saved
        model.addAttribute("resumes", resumeRepository.findAll());
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
        //save resume
        resumeRepository.save(resume);

        // add the resume to that user
        user.getResumes().add(resume);
        // save user
        userRepository.save(user);

        // find job by its id
        job = jobRepository.findById(id).get(); //This might be causing an issue-same name as param above
        // create a new jobUser obj
        JobUser jobUser = new JobUser();
        // save the job and user to the new obj
        jobUser.setJob(job);
        jobUser.setUser(user);
        //save jobUser-must be saved here so that ID is created
        jobUserRepository.save(jobUser);

//Temporarily commented out to make all applications match, remove Boolean matches = true;
        //parse resume and see if it matches 80% of keywords
        //Boolean matches = ParseResume.parseResume(resume, job.getJobTitle());
        Boolean matches = true;

        // if user's resume for that job matches setMatch=true and set appstatus
        if (matches) {

            //save additional info to jobUser
            jobUser.setMatched(true); //this should only be true if matches, else it stays false
            jobUser.setAppStatus("pending interview date");
            // in th:if expression
        }
        else
        {
            jobUser.setMatched(false); //this is the default value
            jobUser.setAppStatus("rejected");
        }
        //save jobUser
        jobUserRepository.save(jobUser);

        // create jobUser_interview object
        JobUser_Interview jui = new JobUser_Interview();
        //set jui jobUser
        jui.setJobUser(jobUser);

        //save jui
        juiRepository.save(jui);

        //add to model
        model.addAttribute("job", job);
        model.addAttribute("jobUser", jobUser);
        model.addAttribute("jui", jui);
        model.addAttribute("matches", matches); //only matches is needed for applied

        if(matches)
        {
            return "success";
        }
        else
        {
            return "fail";
        }
        //final return page is chosen dynamically based on pass or fail of parser
        //return "applied";
    }
}

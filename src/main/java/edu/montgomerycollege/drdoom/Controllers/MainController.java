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

import javax.validation.Valid;
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
    public String upcomingInterviews(Model model)
    {
//        //get all user's jobs
//        List<JobUser_Interview> jobUser_interviews = createCollection();
//        //create list for final list of jobs being interviewed for
//        List<JobUser_Interview> finalJuIs = new ArrayList<JobUser_Interview>();
//
//        if(!jobUser_interviews.isEmpty())
//        {
//            for (JobUser_Interview jobUser_interview : jobUser_interviews)
//            {
//                if (jobUser_interview.getJobUser().getAppStatus().equalsIgnoreCase("pending interview"))
//                {
//                    finalJuIs.add(jobUser_interview);
//                }
//            }
//            model.addAttribute("juis", jobUser_interviews);
//        }
//        model.addAttribute("now", new Date());
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
        //User current_user = userService.getUser();

        //initialize collection of JobUser_Interview objects
        List<JobUser_Interview> jobUser_interviews = createCollection();
        //model.addAttribute("jobs", userService.getUser().getJobUsers());
        model.addAttribute("now", new Date());
        model.addAttribute("juis", jobUser_interviews);
        return "myjobs";
    }

    @GetMapping({"/apply/{id}"})
    public String apply(@PathVariable("id") long id, Model model)
    {
        Job job = jobRepository.findById(id).get();

        JobUser jobUser = new JobUser(job, userService.getUser(), "", false);

        jobUserRepository.save(jobUser);  //not sure if this is needed

        model.addAttribute("jobUser", jobUser);
        // added this line to retrieve all resumes saved-
        //TODO change this to a query that only returns the user's resumes
        model.addAttribute("resumes", resumeRepository.findAll());
        return "apply";
    }

    @PostMapping({"/apply"})

    public String applied(@ModelAttribute("jobUser") JobUser jobUser,
                          @ModelAttribute("resumes")Resume resume,

//    public String applied(@Valid @ModelAttribute("resume")Resume resume, BindingResult resultA,
//                          @ModelAttribute("job") Job job, BindingResult resultB,
//                          @RequestParam("jobId") long id,

                          Model model) {

            JobUser jobUserObject = jobUserRepository.findById(jobUser.getId()).get();
        // get user
        User user = userService.getUser();

//hardcoding because I can't get this to work yet
        //get resume object
        //get all resumes
        List<Resume> resumes = resumeRepository.findAllByUser(user);
        //get the first one, because we'd better have one in by now
        Resume randResume = resumes.get(0);

        Resume resumeObject = resumeRepository.findById(randResume.getId()).get();



//Temporarily commented out to make all applications match, remove Boolean matches = true;
        //parse resume and see if it matches 80% of keywords
        //Boolean matches = ParseResume.parseResume(resume, jobUserObject.getJob().getJobTitle());
        Boolean matches = true;

        // if user's resume for that job matches setMatch=true and set appstatus
        if (matches)
        {
            //save additional info to jobUser
            jobUserObject.setMatched(true); //this should only be true if matches, else it stays false
            jobUserObject.setAppStatus("pending interview date");
            // in th:if expression
        }
        else
        {
            //save additional info to jobUser
            jobUserObject.setMatched(false); //this is the default value
            jobUserObject.setAppStatus("rejected");
        }
        //save jobUser
        jobUserRepository.save(jobUserObject);

        // create jobUser_interview object
        JobUser_Interview jui = new JobUser_Interview();
        //set jui jobUser
        jui.setJobUser(jobUserObject);

        //save jui
        juiRepository.save(jui);


        //add to model
        model.addAttribute("job", jobUser.getJob());
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
    @GetMapping("/setinterview/{id}")
    public String showInterview(@PathVariable("id") long id, Model model){

        JobUser jobUser = jobUserRepository.findById(id).get();
        model.addAttribute("jui", juiRepository.findByJobUser(jobUser));
       // model.addAttribute("now", new Date());
        return "chooseInterview";
    }


    @PostMapping ("/setinterview")
    public String setInterviewDate(@ModelAttribute JobUser_Interview jui, Model model)
    {
        //get jui object
        jui=juiRepository.findById(jui.getId()).get();
        //change appStatus
        jui.getJobUser().setAppStatus("pending interview");
        //save jobUser-appStatus changed
        jobUserRepository.save(jui.getJobUser());
        //set LocalDateTime interview object
        //TODO
            //set interviewTime
        //initialize collection of JobUser_Interview objects
        List<JobUser_Interview> jobUser_interviews = createCollection();

        model.addAttribute("now", new Date());
        model.addAttribute("juis", jobUser_interviews);



        return "myjobs";
    }
//Helper methods

    public LocalDateTime convertStringToDate(String sDate)
    {
        return LocalDateTime.parse(sDate);
    }

    private List<JobUser_Interview> createCollection()
    {
        List<JobUser_Interview> jobUser_interviews = new ArrayList<JobUser_Interview>();
        //iterate through all jobUsers, adding their jui to jobUser_interviews
        Collection<JobUser> jobUsers = jobUserRepository.findAllByUser(userService.getUser());
        for(JobUser ju: jobUsers) {
            // do stuff
            jobUser_interviews.add(juiRepository.findByJobUser(ju));
        }

        return jobUser_interviews;
    }

}

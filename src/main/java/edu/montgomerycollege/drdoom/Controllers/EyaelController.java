package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Models.JobUser;
import edu.montgomerycollege.drdoom.Models.JobUser_Interview;
import edu.montgomerycollege.drdoom.Repositories.JobRepository;
import edu.montgomerycollege.drdoom.Repositories.JobUserRepository;
import edu.montgomerycollege.drdoom.Repositories.JobUser_InterviewRepository;
import edu.montgomerycollege.drdoom.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Controller
public class EyaelController{

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobUser_InterviewRepository juiRepository;

    @Autowired
    JobUserRepository jobUserRepository;

    @Autowired
    UserService userService;


    @GetMapping("/setinterview/{id}")
    public String showInterview(@PathVariable("id") long id, Model model){

        JobUser jobUser = jobUserRepository.findById(id).get();
        model.addAttribute("jui", juiRepository.findByJobUser(jobUser));
        model.addAttribute("now", new Date());
        return "chooseInterview";
    }


    @PostMapping ("/setinterview")
    public String setinterviewdate(@ModelAttribute JobUser_Interview jui, Model model)
    {
        //String thisShouldntBeNecessary = jui.getStringInterviewTime();
        //get jui object
        jui=juiRepository.findById(jui.getId()).get();
        //change appStatus
        jui.getJobUser().setAppStatus("pending interview");
        //set LocalDateTime interview object
        jui.setInterviewTime(convertStringToDate(""));
        //save jobUser
        jobUserRepository.save(jui.getJobUser());

        //initialize collection of JobUser_Interview objects
        Collection<JobUser_Interview> jobUser_interviews = new ArrayList<JobUser_Interview>();
        //iterate through all jobUsers, adding their jui to jobUser_interviews
        Iterable<JobUser> jobUsers = jobUserRepository.findAllByUser(userService.getUser());
        Iterator<JobUser> iterator = jobUsers.iterator();
        while(iterator.hasNext())
        {
            jobUser_interviews.add(juiRepository.findByJobUser(iterator.next()));
        }


        //add date and user jobs to model
        model.addAttribute("now", new Date());
        model.addAttribute("juis", jobUser_interviews);



        return "myjobs";
    }

    public LocalDateTime convertStringToDate(String sDate)
    {
        LocalDateTime date = LocalDateTime.parse(sDate);
        return date;
    }

}
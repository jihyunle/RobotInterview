package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Models.JobUser_Interview;
import edu.montgomerycollege.drdoom.Repositories.JobRepository;
import edu.montgomerycollege.drdoom.Repositories.JobUser_InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EyaelController{

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobUser_InterviewRepository juiRepository;


    @GetMapping("/setinterview")
    public String showInterview( Model model){
        model.addAttribute("jui",new JobUser_Interview());
        return "chooseInterview";
    }


    @PostMapping ("/setinterview")
    public String setinterviewdate(@ModelAttribute JobUser_Interview jui, Model model)
    {
        juiRepository.save(jui);

        model.addAttribute("juis", juiRepository.findAll());

        return "index";
    }

}
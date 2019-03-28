package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.Interview;
import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Repositories.InterviewRepository;
import edu.montgomerycollege.drdoom.Repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EyaelController{

    @Autowired
    JobRepository jobRepository;

    @Autowired
    InterviewRepository interviewRepository;

//    @GetMapping("/setinterview")
//    public String showInterview( Model model){
//        model.addAttribute("job",new Job());
//        return "chooseInterview";
//    }
//
//
//        @PostMapping ("/setinterview")
//        public String setinterviewdate(@ModelAttribute Job job, Model model)
//        {
//            jobRepository.save(job);
//
//            model.addAttribute("jobs", jobRepository.findAll());
//            return "index";
//        }
//

    @GetMapping("/setinterview")
    public String showInterview( Model model){
        model.addAttribute("interview",new Interview());
        return "chooseInterview";
    }


    @PostMapping ("/setinterview")
    public String setinterviewdate(@ModelAttribute Interview interview, Model model)
    {
        interviewRepository.save(interview);

        model.addAttribute("interviews", interviewRepository.findAll());
        return "index";
    }

}

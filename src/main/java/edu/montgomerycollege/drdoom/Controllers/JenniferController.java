package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.JobUser;
import edu.montgomerycollege.drdoom.Models.User;
import edu.montgomerycollege.drdoom.Repositories.JobUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JenniferController {

    @Autowired
    JobUserRepository jobUserRepository;
    private User user;

//    @RequestMapping("/checkmatch")
//    public boolean checkMatch(){
//
//    }

    @RequestMapping("/appeal")
    public String appeal(@PathVariable("id") long id, Model model){
        return "appeal";
    }

    @RequestMapping("/myinterviews")
    public String showInterviews(Model model){
        Iterable<JobUser> jobs = jobUserRepository.findAllByUser(user);

        model.addAttribute("jobs", jobs);
        model.addAttribute(user); // may not be needed
        return "myinterviews";

    }
    // missing post method?
//    @PostMapping("/myinterviews")
//    public String processInterviews(Model model, BindingResult result){
//        if (result.hasErrors()){
//            return "myinterviews";
//        }
//        return "redirect:/interview"
//
//    }

}

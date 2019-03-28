package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.JobUser;
import edu.montgomerycollege.drdoom.Models.JobUser_Interview;
import edu.montgomerycollege.drdoom.Models.QuestionAnswer;
import edu.montgomerycollege.drdoom.Models.User;
import edu.montgomerycollege.drdoom.Repositories.JobUserRepository;
import edu.montgomerycollege.drdoom.Repositories.QuestionAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class JenniferController {

    @Autowired
    JobUserRepository jobUserRepository;

    @Autowired
    QuestionAnswerRepository qaRepository;

    private User user;




//    public String(@ModelAttribute JobUser_Interview jui,
//                  @RequestParam("question")String[] questions,
//                  @RequestParam("answer")String[] answers){
//        QuestionAnswer[] chatHistory = new QuestionAnswer[questions.length];
//        for (int i=0; i<chatHistory.length; i++){
//            QuestionAnswer qa = new QuestionAnswer(questions[i], answers[i]);
//            qaRepository.save(qa);
//            chatHistory[i]=qa;
//        }
//    }

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

package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.*;
import edu.montgomerycollege.drdoom.Repositories.InterviewRepository;
import edu.montgomerycollege.drdoom.Repositories.JobInterviewUserRepository;
import edu.montgomerycollege.drdoom.Repositories.JobRepository;
import edu.montgomerycollege.drdoom.Repositories.QuestionRepository;
import edu.montgomerycollege.drdoom.Services.EmailServiceImpl;
import edu.montgomerycollege.drdoom.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@Controller
public class JesseController
{
    @Autowired
    JobRepository jobRepository;

    @Autowired
    EmailServiceImpl email;

    @Autowired
    private UserService userService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    JobInterviewUserRepository jobInterviewUserRepository;




    @GetMapping({"/interview/{id}"})
    public String interviewGet(@PathVariable("id") long id, Model model)
    {
        Job jobObject = jobRepository.findById(id).get();
        model.addAttribute("job", jobObject);

        //find associated JIU
        JobInterviewUser jobInterviewUser = jobInterviewUserRepository.findByUserAndJob(userService.getUser(),
                                                                                        jobObject);
        Interview interview = new Interview();
        //Get (random selection of) questions (that match correctly)
            //to do random, get random number between 1 and length, then that many random numbers (non-repeating)
        // between 1 and length, those questions are selected
        Iterable<Question> questions = questionRepository.findAll();
        Collection<Question> questions1 = new ArrayList<Question>(Arrays.asList());
        for (Question question:questions)
        {
            questions1.add(question);
        }
        interview.setQuestions(questions1);

        jobInterviewUser.setInterview(interview);
        jobInterviewUserRepository.save(jobInterviewUser);
        model.addAttribute("jiu", jobInterviewUser);
       // System.out.println(jobInterviewUser.getInterview().getQuestions());
        return "interview";
    }

    @PostMapping("/interview")
//    public String interviewPost(@ModelAttribute("interview") Interview interview,
//                                @ModelAttribute("job") Job job,
//                                @RequestParam("answers") String[] answers)
    public String interviewPost(@ModelAttribute("jiu")JobInterviewUser jiu, @RequestParam(
            "questions") String[] questions, @RequestParam("answers") String[] answers)
    {
        //because only the id field is in the form, only that is filled in when the object returns.  Everything else
        // is null
        JobInterviewUser jobInterviewUserObject = jobInterviewUserRepository.findById(jiu.getId()).get();
        Interview interviewObject = jobInterviewUserObject.getInterview();
        System.out.println(answers);
        System.out.println(questions);
        //get list of questions
        //Collection<Question> questions = jobInterviewUserObject.getInterview().getQuestions();
        //System.out.println(questions.toString());
//        for (Question question:temp)
//        {
//            question.setAnswerText(answer[i]);
//            i++;
//        }
        //interview.setQuestions(temp);


        return "interviewComplete";
    }


    @RequestMapping("/sendemail")
    public String sendEmailWithOutTemplating() throws UnsupportedEncodingException
    {
        String from = userService.getUser().getEmail();


        email.send(from, "jesseberliner@hotmail.com", "Subject", "body");
        return "index";
    }


}

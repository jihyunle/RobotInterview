package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Repositories.JobRepository;
import edu.montgomerycollege.drdoom.Services.EmailServiceImpl;
import edu.montgomerycollege.drdoom.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.UnsupportedEncodingException;

@Controller
public class JesseController
{
    @Autowired
    JobRepository jobRepository;

    @Autowired
    EmailServiceImpl email;

    @Autowired
    private UserService userService;


    @GetMapping({"/interview/{id}"})
    public String interviewGet(@PathVariable("id") long id, Model model)
    {
        Job jobObject = jobRepository.findById(id).get();
        model.addAttribute("job", jobObject);
        return "interview";
    }


    @RequestMapping("/sendemail")
    public String sendEmailWithOutTemplating() throws UnsupportedEncodingException
    {
        String from = userService.getUser().getEmail();


        email.send(from, "jesseberliner@hotmail.com", "Subject", "body");
        return "index";
    }


}

package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class JesseController
{
    @Autowired
    JobRepository jobRepository;


    @GetMapping({"/interview/{id}"})
    public String interviewGet(@PathVariable("id") long id, Model model)
    {
        Job jobObject = jobRepository.findById(id).get();
        model.addAttribute("job", jobObject);
        return "interview";
    }
}

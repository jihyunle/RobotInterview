package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Repositories.JobRepository;
import edu.montgomerycollege.drdoom.Repositories.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class AyalenehController
{
    @Autowired
    private  JobRepository jobRepository;
    @Autowired
    private JobTitleRepository jobTitleRepository;

    @RequestMapping("/admin")
    public String adminjob(Model model){
        model.addAttribute("job",new Job());
        model.addAttribute("jobTitles",jobTitleRepository.findAll());
        return "admin";
    }
    @PostMapping("/admin")
//    public String adminprocessjob(@Valid Job job, BindingResult result, Model model){
    public String adminprocessjob(Job job, Model model){
//        if(result.hasErrors()){
//            System.out.println(job.getTitle());
//            System.out.println(job.getDatePosted());
//            System.out.println(job.getDescription());
//            return "redirect:/admin";
//        }
        //else {
            jobRepository.save(job);
        //}
        return "redirect:/";
    }

    @GetMapping("/success")
    public String success(){
        return "success";
    }

    @GetMapping("/fail")
    public String fail(){
        return "fail";
    }

    @GetMapping("/jobs_admin")
    public String adminJob(Model model){
        model.addAttribute("jobs",jobRepository.findAll());
        //This now returns jobs-with thymeleaf security it can be the same page as the regular one
        return "adminjobs";
    }

    @GetMapping("/edit/{id}")
    public String updateJob(@PathVariable("id") long id, Model model){
        model.addAttribute("jobTitles",jobTitleRepository.findAll());
        model.addAttribute("job",jobRepository.findById(id).get());
        return "admin";
    }
    @RequestMapping("/delete/{id}")
    public String deleteJob(@PathVariable("id") long id, Model model){
        model.addAttribute("job",jobRepository.findById(id));
        jobRepository.deleteById(id);
        return "redirect:/jobs_admin";
    }
}

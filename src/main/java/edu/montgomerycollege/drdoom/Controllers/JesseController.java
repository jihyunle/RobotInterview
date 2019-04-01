package edu.montgomerycollege.drdoom.Controllers;

import edu.montgomerycollege.drdoom.Models.*;
import edu.montgomerycollege.drdoom.Repositories.JobRepository;
import edu.montgomerycollege.drdoom.Repositories.JobUserRepository;
import edu.montgomerycollege.drdoom.Repositories.JobUserInterviewRepository;
import edu.montgomerycollege.drdoom.Repositories.QuestionAnswerRepository;
import edu.montgomerycollege.drdoom.Services.EmailServiceImpl;
import edu.montgomerycollege.drdoom.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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
    JobUserRepository jobUserRepository;

    @Autowired
    JobUserInterviewRepository juiRepository;

    @Autowired
    QuestionAnswerRepository questionAnswerRepository;


    @GetMapping({"/interview/{id}"})
    public String interviewGet(@PathVariable("id") long jobUserId, Model model)
    {
        //get jobUser
        JobUser jobUser = jobUserRepository.findById(jobUserId).get();
        //create a new instance of jobUserInterview
        JobUserInterview jobUserInterview = juiRepository.findByJobUser(jobUser);

        //set jobUserIntervew's jobUser
        jobUserInterview.setJobUser(jobUser);


        //get jobTitle to get specific questions
        JobTitle title = jobUser.getJob().getJobTitle();

        //Get selection of questions (that match correctly)
        Iterable<QuestionAnswer> questions = questionAnswerRepository.findAllByJobTitles(title);
        Iterator<QuestionAnswer> iter = questions.iterator();
        Collection<QuestionAnswer> copy = new ArrayList<QuestionAnswer>();
        while (iter.hasNext())
        {
            copy.add(iter.next());
        }
        //TODO
        //add behavioral questions
        //add to collection


        jobUserInterview.setChatHistory(copy); //This saves it, but can't get the object on the other side-it's null

        //save jui
        juiRepository.save(jobUserInterview);

        //add jobUserInterview to model
        model.addAttribute("jui", jobUserInterview);
        //add questions to model
        model.addAttribute("questions", questions);
        return "interview";
    }

    @PostMapping("/interview")
    public String processInterview(@ModelAttribute("jui") JobUserInterview jobUserInterview,
                                   @RequestParam("answers") String[] answers,
                                   Model model)
    {

        JobUserInterview temp = juiRepository.findById(jobUserInterview.getId()).get();
        //chatHistory is currently Null
        //iterate through questions and answers, saving the answer to the object
        Iterable<QuestionAnswer> questions = questionAnswerRepository.findAll();
        Iterator<QuestionAnswer> iter = questions.iterator();
        Collection<QuestionAnswer> copy = new ArrayList<QuestionAnswer>();
        int i = 0;
        while (iter.hasNext())
        {
            QuestionAnswer tempqa = iter.next();
            tempqa.setAnswer(answers[i]);
            i++;
            questionAnswerRepository.save(tempqa);
            copy.add(tempqa);
        }
        jobUserInterview.setChatHistory(copy);

        JobUser jobUser = jobUserInterview.getJobUser();
        jobUser.setAppStatus("Pending offer");
        jobUserInterview.setJobUser(jobUser);
        jobUserRepository.save(jobUser);
        juiRepository.save(jobUserInterview);


        model.addAttribute("jui", jobUserInterview);
        try
        {
            String qaText = getStringVal(jobUserInterview.getChatHistory());
            sendEmailWithAttachment(qaText, jobUserInterview);
        }
        catch (IOException e)
        {
            //don't send if there's an io exception

            System.out.println(e);
        }
        return "interviewOver";
    }


    public void sendEmailWithAttachment(String attach, JobUserInterview jobUserInterview) throws UnsupportedEncodingException,
                                                                                                  IOException
    {

        Files.write(Paths.get("textFile.txt"),
                    attach.getBytes());

        File file = new File("textFile.txt");
        //String toEmail = jobUserInterview.getJobUser().getJob().getHiringManagerEmail();
        //Job job = jobUserInterview.getJobUser().getJob();
        String toEmail = "jesseberliner@hotmail.com";
        if (toEmail == null)
        {
            toEmail = "jesseberliner@gmail.com";
        }


        email.send("whatever@whatever.com", toEmail, "Interview Text", "See attached file", file);
    }

    public String getStringVal(Collection<QuestionAnswer> collection)
    {
        String fullText = "";
        for (QuestionAnswer qa : collection)
        {
            fullText+=qa.getQuestion() + "\n" + qa.getAnswer() +"\n";
        }
        return fullText;
    }
}



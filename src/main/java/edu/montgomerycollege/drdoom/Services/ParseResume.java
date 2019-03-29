package edu.montgomerycollege.drdoom.Services;

import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Models.Keyword;
import edu.montgomerycollege.drdoom.Models.Resume;
import edu.montgomerycollege.drdoom.Models.User;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Collection;

@Service
public class ParseResume
{
    public static boolean parseResume(Resume resume, Job job){

        //get resume
        String resumeString = resume.getResumeData();

        //get keywords
        Collection<Keyword> keywords = job.getKeywords();

        //count number of matches
        int matches = 0;
        //search resumeString for keywords, increase COUNT when/if found
        for (Keyword keywordObject : keywords) {
            if (resumeString.toLowerCase().contains(keywordObject.getKword().toLowerCase())){
                matches ++;
                System.out.println(matches);
            }
        }

        //check against total
        if(matches >= (.8*keywords.size()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /*public void method(){
        User user=new User();
        System.out.println(user.getId());
        System.out.println(user.getAppStatus());
        System.out.println(user.getEmail());
        System.out.println(user.isEnabled());
        System.out.println(user.getFirstName());
        System.out.println(user.getInterviewTime());
        System.out.println(user.getLastName());
        System.out.println(user.getPassword());
        System.out.println(user.getPhoneNumber());
        System.out.println(user.getUsername());
        for(Job job:user.getJobs()){
            System.out.println(job);
        }
        System.out.println(user.getResume().getResumeData());
        Resume resume=new Resume();
        System.out.println(resume.getId());
        System.out.println(resume.getUser());
        System.out.println(resume.getResumeData());
        Keyword kd=new Keyword();
        kd.getKid();
        kd.
        kd.getKword();
    }*/
}

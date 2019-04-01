package edu.montgomerycollege.drdoom.Services;



import edu.montgomerycollege.drdoom.Models.*;

import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Collection;

@Service
public class ParseResume
{
    public static boolean parseResume(Resume resume, JobTitle jobTitle){

        //get resume
        String resumeString = resume.getResumeData();
        System.out.println("resume data is "+resumeString);

        //get keywords
        Collection<Keyword> keywords = jobTitle.getKeywords();

        //count number of matches
        int matches = 0;
        //search resumeString for keywords, increase COUNT when/if found

        System.out.println("the size of the keyword is "+keywords.size());
        for (Keyword keywordObject : keywords) {
            System.out.println("keyword "+keywordObject.getKeyword());
            System.out.println(resumeString);
            if (resumeString.toLowerCase().contains(keywordObject.getKeyword().toLowerCase())){
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

}

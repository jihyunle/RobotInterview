package edu.montgomerycollege.drdoom.Services;

import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Models.Keyword;
import edu.montgomerycollege.drdoom.Models.Resume;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Collection;

@Service
public class ParseResume
{
    public boolean parseResume(Resume resume, Job job){
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

package edu.montgomerycollege.drdoom.Repositories;

import edu.montgomerycollege.drdoom.Models.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Set;

public interface JobRepository extends CrudRepository<Job, Long> {
//    Job findByJobInterviewUsers(String jobInterviewUser);

//    Job findByKeyword(String keyword);
//    Job findByKeywords(ArrayList<String> keywords);
    //Set<Job> findAllByJobInterviewUsers();
}

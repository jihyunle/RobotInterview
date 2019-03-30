package edu.montgomerycollege.drdoom.Repositories;

import edu.montgomerycollege.drdoom.Models.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Set;

public interface JobRepository extends CrudRepository<Job, Long> {
//    Job findByJobUser(String jobUser);

//  Job findById(Long id);
//  Job findByTitle(String title);
  Job findByJobTitle(String jobTitle);


//    Job findByKeyword(String keyword);

    Job findByjobTitle(String jobTitle);
}

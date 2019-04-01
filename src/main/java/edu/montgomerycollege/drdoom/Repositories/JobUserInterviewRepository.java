package edu.montgomerycollege.drdoom.Repositories;

import edu.montgomerycollege.drdoom.Models.JobUser;
import edu.montgomerycollege.drdoom.Models.JobUserInterview;
import org.springframework.data.repository.CrudRepository;

public interface JobUserInterviewRepository extends CrudRepository<JobUserInterview, Long> {
    JobUserInterview findByJobUser(JobUser jobUser);
}

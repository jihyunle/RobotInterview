package edu.montgomerycollege.drdoom.Repositories;

import edu.montgomerycollege.drdoom.Models.JobUser;
import edu.montgomerycollege.drdoom.Models.JobUser_Interview;
import org.springframework.data.repository.CrudRepository;

public interface JobUser_InterviewRepository extends CrudRepository<JobUser_Interview, Long> {
    JobUser_Interview findByJobUser(JobUser jobUser);
}

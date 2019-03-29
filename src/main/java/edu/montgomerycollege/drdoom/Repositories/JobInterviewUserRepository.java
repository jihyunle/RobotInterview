package edu.montgomerycollege.drdoom.Repositories;

import org.springframework.data.repository.CrudRepository;

public interface JobInterviewUserRepository extends CrudRepository<JobInterviewUserRepository, Long> {
    JobInterviewUserRepository findByJobUser(JobInterviewUserRepository jobInterviewUser );
}


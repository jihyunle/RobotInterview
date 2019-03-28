package edu.montgomerycollege.drdoom.Repositories;

import edu.montgomerycollege.drdoom.Models.JobInterviewUser;
import edu.montgomerycollege.drdoom.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface JobInterviewUserRepository extends CrudRepository<JobInterviewUser, Long> {

}

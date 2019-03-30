package edu.montgomerycollege.drdoom.Repositories;

import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Models.JobUser;
import edu.montgomerycollege.drdoom.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface JobUserRepository extends CrudRepository<JobUser, Long> {
    Collection<JobUser> findAllByUser(User user);
    Collection<JobUser> findAllByJob(Job job);
    JobUser findByJobAndUser(Job job, User user);
    JobUser findByAppStatus(String appStatus);
}

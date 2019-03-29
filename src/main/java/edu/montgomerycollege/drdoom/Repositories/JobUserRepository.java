package edu.montgomerycollege.drdoom.Repositories;

import edu.montgomerycollege.drdoom.Models.JobUser;
import edu.montgomerycollege.drdoom.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface JobUserRepository extends CrudRepository<JobUser, Long> {
    Iterable<JobUser> findAllByUser(User user);
    JobUser findByAppStatus(String appStatus);
}

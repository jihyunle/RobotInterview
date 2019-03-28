package edu.montgomerycollege.drdoom.Repositories;

import edu.montgomerycollege.drdoom.Models.Interview;
import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Models.JobInterviewUser;
import edu.montgomerycollege.drdoom.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface JobInterviewUserRepository extends CrudRepository<JobInterviewUser, Long> {

    Iterable<JobInterviewUser> findAllByUser(User user); // return all JIU associated with the user

    JobInterviewUser findByJobTitle(String jobInterviewUser);

    Job findByJobId();
    Job findByTitle(String jobTitle);

    Interview findByInterviewId(String interviewId);
//    Interview findByDate(String interviewDate); // gives error

    User findByUsername(String username);
    User findByEmail(String email);


}

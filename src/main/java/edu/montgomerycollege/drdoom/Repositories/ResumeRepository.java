package edu.montgomerycollege.drdoom.Repositories;

import edu.montgomerycollege.drdoom.Models.Resume;
import edu.montgomerycollege.drdoom.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResumeRepository extends CrudRepository<Resume, Long>
{
    Resume findByResumeVersionName(String resumeVersionName);

    List<Resume> findAllByUser(User user);
}

package edu.montgomerycollege.drdoom.Repositories;

import edu.montgomerycollege.drdoom.Models.Interview;
import edu.montgomerycollege.drdoom.Models.Keyword;
import org.springframework.data.repository.CrudRepository;

public interface InterviewRepository extends CrudRepository<Interview, Long> {
}

package edu.montgomerycollege.drdoom.Repositories;

import edu.montgomerycollege.drdoom.Models.JobTitle;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface JobTitleRepository extends CrudRepository<JobTitle,Long> {
     JobTitle findByKeywords(ArrayList<String> keywords);//select * from job where keywords=?
}

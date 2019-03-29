package edu.montgomerycollege.drdoom.Repositories;

import edu.montgomerycollege.drdoom.Models.Job;
import edu.montgomerycollege.drdoom.Models.Keyword;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface KeywordRepository extends CrudRepository<Keyword, Long> {

}

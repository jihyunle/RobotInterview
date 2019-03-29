package edu.montgomerycollege.drdoom.Repositories;

import edu.montgomerycollege.drdoom.Models.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface QuestionRepository extends CrudRepository<Question, Long>
{

}

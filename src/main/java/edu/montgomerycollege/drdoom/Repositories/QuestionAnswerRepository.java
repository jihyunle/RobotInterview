package edu.montgomerycollege.drdoom.Repositories;

import edu.montgomerycollege.drdoom.Models.QuestionAnswer;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface QuestionAnswerRepository extends CrudRepository<QuestionAnswer, Long> {
    Collection<QuestionAnswer> findAllByAnswerIsNull();
}

package Controller;

import Entity.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface QuestionAnswerRepo extends CrudRepository<Question, Long> {

    Optional<Question> findById(Long id);

}

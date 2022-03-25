package questionnaire.Controller;

import questionnaire.Entity.Questionnaire;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "questionnaire", path = "questionnaire")
public interface QuestionnaireRepo extends PagingAndSortingRepository<Questionnaire, Long>{

    Questionnaire findById(long id);

    List<Questionnaire> findAllByOrderByIdAsc();

}

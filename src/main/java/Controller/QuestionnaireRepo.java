package Controller;

import Entity.Questionnaire;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "questionnaire", path = "questionnaire")
public interface QuestionnaireRepo extends PagingAndSortingRepository<Questionnaire, Long>{

    Questionnaire findById(long id);
}

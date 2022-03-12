package Controller;

import Entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepo extends PagingAndSortingRepository<User, Long>{

    User findById(long id);

}

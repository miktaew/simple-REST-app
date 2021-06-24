package simple.app.repository;

import org.springframework.data.repository.CrudRepository;
import simple.app.model.Client;

public interface ClientRepository extends CrudRepository<Client, String> {
}

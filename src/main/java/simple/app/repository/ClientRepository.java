package simple.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import simple.app.misc.ClientNonConfidential;
import simple.app.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByClientLogin(String login);

    // gets the non-confidential data of employees (no login or access code)
    @Query("SELECT c.clientId as clientId, c.firstName as firstName, c.lastName as lastName FROM Client c")
    List<ClientNonConfidential> getClientsNonConfidential();

    @Query("SELECT c.clientId as clientId, c.firstName as firstName, c.lastName as lastName FROM Client c WHERE c.clientId = ?1")
    Optional<ClientNonConfidential> getClientByIdNonConfidential(Long id);
}

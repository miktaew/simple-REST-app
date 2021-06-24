package simple.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import simple.app.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Employee findByEmployeeLogin(String login);
}

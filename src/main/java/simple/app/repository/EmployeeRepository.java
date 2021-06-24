package simple.app.repository;

import org.springframework.data.repository.CrudRepository;
import simple.app.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String> {

}

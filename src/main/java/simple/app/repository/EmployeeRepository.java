package simple.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import simple.app.misc.EmployeeNonConfidential;
import simple.app.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Employee findByEmployeeLogin(String login);

    // gets the non-confidential data of employees (no login or access code)
    @Query("SELECT e.employeeId as employeeId, e.firstName as firstName, e.lastName as lastName, e.isAdmin as isAdmin FROM Employee e")
    List<EmployeeNonConfidential> getEmployeesNonConfidential();

    @Query("SELECT e.employeeId as employeeId, e.firstName as firstName, e.lastName as lastName, e.isAdmin as isAdmin FROM Employee e WHERE e.employeeId = ?1")
    Optional<EmployeeNonConfidential> getEmployeeByIdNonConfidential(Long id);
    
}

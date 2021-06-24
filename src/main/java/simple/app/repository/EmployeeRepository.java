package simple.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import simple.app.misc.EmployeeNonConfidential;
import simple.app.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmployeeLogin(String login);

    // gets the non-confidential data of employees (no login or access code)
    @Query("SELECT e.firstName as firstName, e.lastName as lastName, e.isAdmin as isAdmin FROM Employee e")
    List<EmployeeNonConfidential> getEmployeeNonConfidential();
}

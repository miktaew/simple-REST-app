package simple.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.app.misc.EmployeeNonConfidential;
import simple.app.model.Employee;
import simple.app.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees()
    {
        List<Employee> employeeRecords = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeRecords::add);
        return employeeRecords;
    }

    public List<EmployeeNonConfidential> getAllEmployeesNonConfidential()
    {
        List<EmployeeNonConfidential> employeeRecords = new ArrayList<>();
        employeeRepository.getEmployeesNonConfidential().forEach(employeeRecords::add);
        return employeeRecords;
    }

    public Optional<EmployeeNonConfidential> getEmployeeByIdNonConfidential(Long id)
    {
        return employeeRepository.getEmployeeByIdNonConfidential(id);
    }

    public Employee getEmployeeByLogin(String login) {
        return employeeRepository.findByEmployeeLogin(login);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public void addEmployee(Employee employee)
    {
        employeeRepository.save(employee);
    }

    public Boolean validateAccess(String login, String accessCode)
    {
        Employee employee = getEmployeeByLogin(login);
        if(employee != null && employee.getEmployeeAccessCode().equals(accessCode))
        {
            return true;
        } else {
            return false;
        }
    }
    public Boolean validateAdminAccess(String login, String accessCode)
    {
        Employee employee = getEmployeeByLogin(login);
        if(employee != null && employee.getEmployeeAccessCode().equals(accessCode) && employee.getIsAdmin())
        {
            return true;
        } else {
            return false;
        }
    }
}

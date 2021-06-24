package simple.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.app.model.Employee;
import simple.app.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

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

    public Employee getEmployeeByLogin(String login) {
        
    }

    public void addEmployee(Employee employee)
    {
        employeeRepository.save(employee);
    }
}

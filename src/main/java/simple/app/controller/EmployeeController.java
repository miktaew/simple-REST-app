package simple.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import simple.app.misc.EmployeeNonConfidential;
import simple.app.model.Employee;
import simple.app.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    @ResponseBody
    private List<EmployeeNonConfidential> getAllEmployees(@RequestParam String userLogin, @RequestParam String accessCode) {
        // todo: dont return id, login and access code
        Employee employee = employeeService.getEmployeeByLogin(userLogin);
        if(employee != null && employee.getEmployeeAccessCode().equals(accessCode)) {
            List<EmployeeNonConfidential> result = employeeService.getAllEmployeesNonConfidential();
            return result;
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/employees/{id}")
    @ResponseBody
    private Employee getEmployee(@RequestParam String userLogin, @RequestParam String accessCode, @PathVariable long id) {
        // todo: dont return id, login and access code
        Employee employee = employeeService.getEmployeeByLogin(userLogin);
        if(employee != null && employee.getEmployeeAccessCode().equals(accessCode)) {
            Optional<Employee> result = employeeService.getEmployeeById(id);
            if (result.isPresent()) {
                return result.get();
            } else {
                return null;
            }
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value="/add-employee", method = RequestMethod.POST)
    public void addEmployee(@RequestBody Employee newEmployee, @RequestBody String userLogin, @RequestBody String accessCode)
    {
        Employee employee = employeeService.getEmployeeByLogin(userLogin);
        if(employee != null && employee.getEmployeeAccessCode().equals(accessCode) && employee.getIsAdmin()) {
            employeeService.addEmployee(employee);
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}

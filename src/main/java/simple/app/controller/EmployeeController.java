package simple.app.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;
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
    private List<Employee> getAllEmployees(@RequestParam String userLogin, @RequestParam String accessCode) {
        // todo: authorization
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    @ResponseBody
    private Employee getEmployee(@RequestParam String userLogin, @RequestParam String accessCode, @PathVariable long id) {
        Employee employee = employeeService.getEmployeeByLogin(userLogin);
        if(employee.getEmployeeAccessCode().equals(accessCode)) {
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
    public void addEmployee(@RequestBody Employee employee, @RequestBody String userLogin, @RequestBody String accessCode)
    {
        // todo: authorization
        employeeService.addEmployee(employee);
    }

}

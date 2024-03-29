package simple.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
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

    //gets list of all employees (without their ids, logins and access codes)
    @GetMapping("/employees")
    @ResponseBody
    private List<EmployeeNonConfidential> getAllEmployees(@RequestParam String userLogin, @RequestParam String accessCode) {
        if(employeeService.validateAccess(userLogin, accessCode)) {
            return employeeService.getAllEmployeesNonConfidential();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    //gets employee by id (without login or access code)
    @GetMapping("/employees/{employeeId}")
    @ResponseBody
    private EmployeeNonConfidential getEmployeeById(@RequestParam String userLogin, @RequestParam String accessCode, @PathVariable Long employeeId) {
        if(employeeService.validateAccess(userLogin, accessCode)) {
            Optional<EmployeeNonConfidential> result = employeeService.getEmployeeByIdNonConfidential(employeeId);
            if (result.isPresent()) {
                return result.get();
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    //adds new employee
    @PostMapping("/add-employee")
    public String addEmployee(@RequestBody Employee newEmployee, @RequestParam String userLogin, @RequestParam String accessCode)
    {
        //todo: change access code to something generated by server, instead of simple passwords (and return it on success)

        if(employeeService.getEmployeeByLogin(newEmployee.getEmployeeLogin()) != null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Login already in use");
        }

        if(employeeService.validateAdminAccess(userLogin, accessCode)) {
            employeeService.addEmployee(newEmployee);
            return "Employee added successfully";
        }
        else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}

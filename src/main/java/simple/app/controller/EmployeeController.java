package simple.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import simple.app.model.Employee;
import simple.app.service.EmployeeService;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    @ResponseBody
    private List<Employee> getAllEmployees(@RequestParam String userLogin, @RequestParam String accessCode) {
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value="/add-employee", method = RequestMethod.POST)
    public void addEmployee(@RequestBody Employee employee)
    {
        employeeService.addEmployee(employee);
    }

}

package pro.sky.employeebook;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public String welcomeToEmployee() {
        return "Welcome to Employees page!";
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName) {
        Employee newEmployee = employeeService.addEmployee(firstName, lastName);
        return newEmployee;

    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName) {
        Employee foundedEmployee = employeeService.findEmployee(firstName, lastName);
        return foundedEmployee;
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        Employee removedEmployee = employeeService.removeEmployee(firstName, lastName);
        return removedEmployee;
    }
}

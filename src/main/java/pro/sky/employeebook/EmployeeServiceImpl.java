package pro.sky.employeebook;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private int size;
    private Employee[] employees = new Employee[5];

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.length == size) {
            throw new NoFreeSpotsAvaliableException("There is no place for new employees");
        }
        Employee newEmployee = new Employee(firstName, lastName);
        if (getEmployeeIndex(newEmployee) != -1) {
            throw new EmployeeAlreadyExistException("This employee already exists");
        }
        employees[size++] = newEmployee;
        return newEmployee;

    }

    private int getEmployeeIndex(Employee employee) {
        for (int i = 0; i < size; i++) {
            if (employee.equals(employees[i])) {
                return i;
            }
        }
        return -1;
    }

    private boolean employeeExist(Employee employee) {
        boolean employeeExist = true;
        if (getEmployeeIndex(employee) == -1) {
            employeeExist = false;
        }
        return employeeExist;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee foundEmployee = new Employee(firstName, lastName);
        if (getEmployeeIndex(foundEmployee) == 1) {
            throw new EmployeeIsMissingException("This employee is missing");
        }
        for (int i = 0; i < employees.length; i++) {
            Employee employee = employees[i];
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                foundEmployee = employee;
            }
        }
        return foundEmployee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        int employeeIndex = getEmployeeIndex(employeeToRemove);
        Employee removedEmployee = employees[employeeIndex];
        if (employeeIndex == -1) {
            throw new EmployeeIsMissingException("Cannot delete missing employee");
        }
        System.arraycopy(employees, employeeIndex +1, employees, employeeIndex, size - employeeIndex );
        size--;

        return removedEmployee;
    }
}

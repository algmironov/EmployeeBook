package pro.sky.employeebook.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.employeebook.data.Employee;
import pro.sky.employeebook.exceptions.EmployeeAlreadyExistException;
import pro.sky.employeebook.exceptions.EmployeeIsMissingException;
import pro.sky.employeebook.exceptions.NoEmployeesInListException;
import pro.sky.employeebook.service.EmployeeService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static int size;
    private final Map<String, Employee> employeeBook = new HashMap<>();

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
            if (employeeBook.containsKey(firstName + lastName)) {
                throw new EmployeeAlreadyExistException("This employee already exists");
            } else {
                employeeBook.put(firstName + lastName, newEmployee);
                size++;
                return newEmployee;
            }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        if (!employeeBook.containsKey(firstName + lastName)) {
            throw new EmployeeIsMissingException("This employee is missing");
        }
        return employeeBook.get(firstName + lastName);
    }

    public Employee removeEmployee(String firstName, String lastName) {
        if (!employeeBook.containsKey(firstName + lastName)) {
            throw new EmployeeIsMissingException("Cannot delete missing employee");
        }
        employeeBook.remove(firstName + lastName);
        size--;
        return employeeBook.get(firstName + lastName);
    }

    @Override
    public Collection<Employee> printAllEmployees() {
        if (size == 0) {
            throw new NoEmployeesInListException("Employee list is empty");
        }
        return employeeBook.values();
    }

    @Override
    public Integer getSize() {
        return size;
    }
}

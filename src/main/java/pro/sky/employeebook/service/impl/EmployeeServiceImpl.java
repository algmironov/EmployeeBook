package pro.sky.employeebook.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.employeebook.data.Employee;
import pro.sky.employeebook.exceptions.EmployeeAlreadyExistException;
import pro.sky.employeebook.exceptions.EmployeeIsMissingException;
import pro.sky.employeebook.exceptions.NoEmployeesInListException;
import pro.sky.employeebook.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private int size;
    private List<Employee> employeeList = new ArrayList<>();

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        for (Employee employee : employeeList) {
            if (newEmployee.equals(employee)) {
                throw new EmployeeAlreadyExistException("This employee already exists");
            }
        }
        employeeList.add(newEmployee);
        size++;
        return newEmployee;

    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee foundEmployee = new Employee(firstName, lastName);
        if (!employeeList.contains(foundEmployee)) {
            throw new EmployeeIsMissingException("This employee is missing");
        }
        return foundEmployee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employeeList.contains(employeeToRemove)) {
            throw new EmployeeIsMissingException("Cannot delete missing employee");
        }
        employeeList.remove(employeeToRemove);
        size--;

        return employeeToRemove;
    }

    @Override
    public List<Employee> printAllEmployees() {
        if (size == 0) {
            throw new NoEmployeesInListException("Employee list is empty");
        }
        return employeeList;
    }

    @Override
    public Integer getSize() {
        return size;
    }
}

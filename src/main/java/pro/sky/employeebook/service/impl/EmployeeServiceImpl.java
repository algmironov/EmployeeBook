package pro.sky.employeebook.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.employeebook.data.Employee;
import pro.sky.employeebook.exceptions.EmployeeAlreadyExistException;
import pro.sky.employeebook.exceptions.EmployeeIsMissingException;
import pro.sky.employeebook.exceptions.NoEmployeesInListException;
import pro.sky.employeebook.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private int size;
    private final Map<Integer, Employee> employeeBook = new HashMap<>();

    private int getNewId() {
        int newId = 0;
        newId += employeeBook.size();
        return newId;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
            if (employeeBook.containsValue(newEmployee)) {
                throw new EmployeeAlreadyExistException("This employee already exists");
            } else {
                employeeBook.put(getNewId(), newEmployee);
                size++;
                return newEmployee;
            }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee foundEmployee = new Employee(firstName, lastName);
        if (!employeeBook.containsValue(foundEmployee)) {
            throw new EmployeeIsMissingException("This employee is missing");
        }
        return foundEmployee;
    }

    private int getEmployeeId(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employeeBook.containsValue(employee)) {
            throw new EmployeeIsMissingException("Cannot delete missing employee");
        }
        int id = 0;
        int employeeId = 0;
        for (; id < employeeBook.size() - 1; id++) {
            if (employeeBook.get(id).equals(employee)) {
                employeeId += id;
            }
        }

        return employeeId;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employeeBook.containsValue(employeeToRemove)) {
            throw new EmployeeIsMissingException("Cannot delete missing employee");
        }
        employeeBook.remove(getEmployeeId(firstName, lastName),employeeToRemove);
        size--;
        return employeeToRemove;
    }

    @Override
    public Map<Integer, Employee> printAllEmployees() {
        if (size == 0) {
            throw new NoEmployeesInListException("Employee list is empty");
        }
        return employeeBook;
    }

    @Override
    public Integer getSize() {
        return size;
    }
}

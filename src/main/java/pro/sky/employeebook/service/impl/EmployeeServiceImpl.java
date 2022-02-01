package pro.sky.employeebook.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.employeebook.data.Employee;
import pro.sky.employeebook.exceptions.EmployeeAlreadyExistException;
import pro.sky.employeebook.exceptions.EmployeeIsMissingException;
import pro.sky.employeebook.exceptions.NoEmployeesInListException;
import pro.sky.employeebook.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static int size;
    private final transient Map<Employee, Object> employeeBook;
    public EmployeeServiceImpl() {employeeBook = new HashMap<>();}
    private static final Object PRESENT = new Object();

    /*private int getNewId() {
        int newId = size;
        return newId;
    }
*/
    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
            if (employeeBook.containsKey(newEmployee)) {
                throw new EmployeeAlreadyExistException("This employee already exists");
            } else {
                employeeBook.put(newEmployee, PRESENT);
                size++;
                return newEmployee;
            }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeBook.containsKey(employee)) {
            return employee;
        } else {
            throw new EmployeeIsMissingException("This employee is empty");
        }
    }

//    private int getEmployeeId(String firstName, String lastName) {
//        Employee employee = new Employee(firstName, lastName);
//        if (!employeeBook.containsValue(employee)) {
//            throw new EmployeeIsMissingException("This employee is missing");
//        }
//        int id = 0;
//        int employeeId = 0;
//        for (; id < employeeBook.size() - 1; id++) {
//            if (employeeBook.get(id) == null) {
//                continue;
//            } else  if (employeeBook.get(id).equals(employee)) {
//                employeeId += id;
//            }
//        }
//        return employeeId;
//    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employeeBook.containsKey(employeeToRemove)) {
            throw new EmployeeIsMissingException("Cannot delete missing employee");
        }
        employeeBook.remove(employeeToRemove, PRESENT);
        size--;
        return employeeToRemove;
    }

    @Override
    public Set<Employee> printAllEmployees() {
        if (size == 0) {
            throw new NoEmployeesInListException("Employee list is empty");
        }
        return employeeBook.keySet();
    }

    @Override
    public Integer getSize() {
        return size;
    }
}

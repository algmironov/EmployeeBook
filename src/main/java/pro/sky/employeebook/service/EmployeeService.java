package pro.sky.employeebook.service;

import pro.sky.employeebook.data.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee findEmployee(String firstName, String lastName);

    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Collection<Employee> printAllEmployees();

    Integer getSize();

}

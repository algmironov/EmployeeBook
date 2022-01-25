package pro.sky.employeebook;

import org.apache.catalina.util.Strftime;

import java.awt.*;

public interface EmployeeService {
    Employee findEmployee(String firstName, String lastName);

    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

}

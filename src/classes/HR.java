/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

//This sub class is implementing INHERITANCE from the base class Employee.

import java.util.ArrayList;


public class HR extends Employee {

    public HR() {
        this.employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void deleteEmployee(String emp_ID) {
        employeeList.removeIf(employee -> employee.getEmp_ID().equals(emp_ID));
    }
    public void updateEmployee(Employee employee) {
        for (int i = 0; i < employeeList.size(); i++) {
            if (employeeList.get(i).emp_ID.equals(employee.emp_ID)) {
                employeeList.set(i, employee);
                return;
            }
        }
    }
}
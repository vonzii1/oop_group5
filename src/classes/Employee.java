/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.ArrayList;
import java.util.List;

//This class is implementing ENCAPSULATION.
public class Employee {
    
    String emp_ID;
    private static String emp_FirstName;
    private static String emp_LastName;
    private String emp_DOB;
    private String emp_Address;
    private String emp_PhoneNumber;
    private String emp_Status;
    private String emp_Position;
    private double emp_BasicSalary;
    public String emp_SSSNumber;
    public String emp_PhilHealthNumber;
    public String emp_TINNumber;
    public String emp_PagIbigNumber;
    private String emailAddress;
    private String password;
    private String emp_FullName;
    protected List<Employee> employeeList = new ArrayList<>();
    private Object DriverManager;
    
    private int employeeId;
    private String employeeName;

    /**
     *
     * @param employeeId
     * @param employeeName
     */
    public Employee(int employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + ", Name: " + employeeName;
    }
    
    
    Employee() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getEmp_ID() {
        return emp_ID;
    }

    public void setEmp_ID(String emp_ID) {
        this.emp_ID = emp_ID;
    }

    public static String getEmp_FirstName() {
        return emp_FirstName;
    }

    public static void setEmp_FirstName(String emp_FirstName) {
        Employee.emp_FirstName = emp_FirstName;
    }

    public static String getEmp_LastName() {
        return emp_LastName;
    }

    public static void setEmp_LastName(String emp_LastName) {
        Employee.emp_LastName = emp_LastName;
    }

    public String getEmp_DOB() {
        return emp_DOB;
    }

    public void setEmp_DOB(String emp_DOB) {
        this.emp_DOB = emp_DOB;
    }

    public String getEmp_Address() {
        return emp_Address;
    }

    public void setEmp_Address(String emp_Address) {
        this.emp_Address = emp_Address;
    }

    public String getEmp_PhoneNumber() {
        return emp_PhoneNumber;
    }

    public void setEmp_PhoneNumber(String emp_PhoneNumber) {
        this.emp_PhoneNumber = emp_PhoneNumber;
    }

    public String getEmp_Status() {
        return emp_Status;
    }

    public void setEmp_Status(String emp_Status) {
        this.emp_Status = emp_Status;
    }

    public String getEmp_Position() {
        return emp_Position;
    }

    public void setEmp_Position(String emp_Position) {
        this.emp_Position = emp_Position;
    }

    public double getEmp_BasicSalary() {
        return emp_BasicSalary;
    }

    public void setEmp_BasicSalary(double emp_BasicSalary) {
        this.emp_BasicSalary = emp_BasicSalary;
    }

    public String getEmp_SSSNumber() {
        return emp_SSSNumber;
    }

    public void setEmp_SSSNumber(String emp_SSSNumber) {
        this.emp_SSSNumber = emp_SSSNumber;
    }

    public String getEmp_PhilHealthNumber() {
        return emp_PhilHealthNumber;
    }

    public void setEmp_PhilHealthNumber(String emp_PhilHealthNumber) {
        this.emp_PhilHealthNumber = emp_PhilHealthNumber;
    }

    public String getEmp_TINNumber() {
        return emp_TINNumber;
    }

    public void setEmp_TINNumber(String emp_TINNumber) {
        this.emp_TINNumber = emp_TINNumber;
    }

    public String getEmp_PagIbigNumber() {
        return emp_PagIbigNumber;
    }

    public void setEmp_PagIbigNumber(String emp_PagIbigNumber) {
        this.emp_PagIbigNumber = emp_PagIbigNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    Object getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String getFullName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
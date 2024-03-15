/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import static classes.Earning.calculateGrossIncome;

/**
 *
 * @author User
 */
public class MonthlySummaryReport {
    
    public int emp_ID;
    public String emp_FullName;
    public String position;
    public String department;
    public double dailyRate;
    public int daysWorked;
    public double overtimePay;
    public double grossIncome = calculateGrossIncome( dailyRate, daysWorked, overtimePay);
    public int sssNumber;
    public double sssContribution;
    public int philhealthNumber;
    public int philhealthContribution;
    public int pagibigNumber;
    public int pagibigContribution;
    public int tinNumber;
    public int withholdingContribution;
    private Iterable<Employee> employeeList;

    public MonthlySummaryReport(int emp_ID, String emp_FullName, String position, String department, double dailyRate, int daysWorked, double overtimePay, int sssNumber, double sssContribution, int philhealthNumber, int philhealthContribution, int pagibigNumber, int pagibigContribution, int tinNumber, int withholdingContribution) {
        this.emp_ID = emp_ID;
        this.emp_FullName = emp_FullName;
        this.position = position;
        this.department = department;
        this.dailyRate = dailyRate;
        this.daysWorked = daysWorked;
        this.overtimePay = overtimePay;
        this.sssNumber = sssNumber;
        this.sssContribution = sssContribution;
        this.philhealthNumber = philhealthNumber;
        this.philhealthContribution = philhealthContribution;
        this.pagibigNumber = pagibigNumber;
        this.pagibigContribution = pagibigContribution;
        this.tinNumber = tinNumber;
        this.withholdingContribution = withholdingContribution;
       
    }
    
    public static double calculateNetPay(double grossIncome, double sssContribution, int sssNumber, double philhealthContribution, int philhealthNumber, double pagibigContribution,int pagibigNumber, double withholdingContribution, int tinNumber){
       
        double netPay = withholdingContribution + grossIncome + sssContribution + philhealthContribution 
                + pagibigContribution;
        return netPay;  
    }
    
    public void generatePayrollReport() {
    System.out.println("Payroll Report:");
    for (Employee employee : employeeList) {
        System.out.println(employee.getEmp_ID() + " - " + employee.getFullName());
    }
    
}
    
}
    

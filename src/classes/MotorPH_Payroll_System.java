/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package classes;

import frames.frm_EmployeePayslip;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author User
 */


public class MotorPH_Payroll_System {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frm_EmployeePayslip mockPayslipView = new frm_EmployeePayslip();
            mockPayslipView.setVisible(true);

            // Programmatically set fields with test data
            mockPayslipView.jFormattedTextField3.setText("34");  // Payslip Number
            mockPayslipView.jFormattedTextField6.setText("1");   // Employee ID
            mockPayslipView.jFormattedTextField7.setText("Manuel Garcia III");  // Employee Name
            mockPayslipView.jFormattedTextField10.setText("CEO");  // Department

            // Format and parse dates for the test
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
            try {
                Date startDate = sdf.parse("May 01, 2024");
                Date endDate = sdf.parse("June 01, 2024");

                // Instantiate the EmployeePayslip with the form and dates, and generate the payslip
                EmployeePayslip employeePayslip = new EmployeePayslip(mockPayslipView, startDate, endDate);
                employeePayslip.generatePayslip();  // This will trigger the report generation
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(mockPayslipView, "Failed to parse dates: " + e.getMessage(), "Date Parsing Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
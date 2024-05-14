/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author User
 */
import frames.frm_EmployeePayslip;
import java.awt.Button;
import javax.swing.JOptionPane;
import org.w3c.dom.Text;

public class EmployeePayslip {
    
    public frm_EmployeePayslip payslipView;
    public Text jFormattedTextField3;
    public Text jFormattedTextField6;
    public Text jFormattedTextField7;
    public Text jFormattedTextField8;
    public Text jFormattedTextField9;
    public Text jFormattedTextField10;
    public Button jButton1;
    

    public EmployeePayslip(frm_EmployeePayslip payslipView) {
        this.payslipView = payslipView;
        setupListeners();
    }
    
     public void generatePayslip() {
        // Example of collecting data from the form
        String payslipNumber = payslipView.jFormattedTextField3.getText().trim();
        String employeeId = payslipView.jFormattedTextField6.getText().trim();
        String name = payslipView.jFormattedTextField7.getText().trim();
        String startDate = payslipView.jFormattedTextField8.getText().trim();
        String endDate = payslipView.jFormattedTextField9.getText().trim();
        String department = payslipView.jFormattedTextField10.getText().trim();

        // Validation logic
        if (payslipNumber.isEmpty() || employeeId.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            JOptionPane.showMessageDialog(payslipView, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Here you would add the logic to generate the payslip.
        // This could involve calculations based on the dates provided and employee ID, 
        // fetching rates and hours worked, etc.

        // For this example, we'll just show a confirmation message.
        JOptionPane.showMessageDialog(payslipView, "Payslip generated for Employee ID: " + employeeId, "Success", JOptionPane.INFORMATION_MESSAGE);

        // Now you might want to update the view or close it
        payslipView.dispose();
        // Open another view or update the current one
    }

    public void goBack() {
        // Logic to return to the previous screen
        payslipView.dispose();
        // e.g., new frm_MainMenu().setVisible(true);
    }

    private void setupListeners() {
        payslipView.jButton1.addActionListener(evt -> goBack());
    }
}

    // Add methods to handle the business logic for the payslip here


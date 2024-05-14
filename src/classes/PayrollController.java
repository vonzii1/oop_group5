package classes;

import frames.frm_ApprovalCenter;
import frames.frm_ApprovalCenterPayroll;
import frames.frm_EmployeeAttendance;
import java.awt.Button;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * PayrollController class to manage payroll operations.
 */
public class PayrollController {
    private frm_ApprovalCenterPayroll view;
    private Button jButton1;
    private Button jButton2;

    public PayrollController(frm_ApprovalCenterPayroll view) {
        this.view = view;
        setupListeners();
        
    }

    public void generatePayslip() {
        double earnings = calculateEarnings();
        double benefits = calculateBenefits();
        double deductions = calculateDeductions();
        double takeHomePay = calculateTakeHomePay(earnings, benefits, deductions);

        view.setjFormattedTextField14(formatCurrency(earnings));
        view.setjFormattedTextField26(formatCurrency(benefits));
        view.setjFormattedTextField25(formatCurrency(deductions));
        view.setjFormattedTextField20(formatCurrency(takeHomePay));

        // Show confirmation message
        JOptionPane.showMessageDialog(view, "Payslip generated successfully.", "Payslip Generated", JOptionPane.INFORMATION_MESSAGE);
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private double calculateEarnings() {
        Double monthlyRate = parseDouble(view.getjFormattedTextField11().getText());
        double dailyRate = parseDouble(view.getjFormattedTextField12().getText());
        int daysWorked = Integer.parseInt(view.getjFormattedTextField13().getText());
        double overtime = parseDouble(view.getjFormattedTextField15().getText());

        return (monthlyRate + (dailyRate * daysWorked) + overtime);
    }

    private double calculateBenefits() {
        double riceAllowance = parseDouble(view.getjFormattedTextField28().getText());
        double phoneAllowance = parseDouble(view.getjFormattedTextField27().getText());
        double clothingAllowance = parseDouble(view.getjFormattedTextField19().getText());

        return (riceAllowance + phoneAllowance + clothingAllowance);
    }

    private double calculateDeductions() {
        double sss = parseDouble(view.getjFormattedTextField21().getText());
        double philHealth = parseDouble(view.getjFormattedTextField22().getText());
        double pagIbig = parseDouble(view.getjFormattedTextField23().getText());
        double tax = parseDouble(view.getjFormattedTextField24().getText());

        return (sss + philHealth + pagIbig + tax);
    }

    private double calculateTakeHomePay(double earnings, double benefits, double deductions) {
        return earnings + benefits - deductions;
    }

    private String formatCurrency(double amount) {
        return String.format("₱%,.2f", amount);
    }

    private void setupListeners() {
        
        jButton2.addActionListener(e -> generatePayslip());
        view.getContentPane().setLayout(new java.awt.BorderLayout()); // Set the layout manager to BorderLayout
        view.getContentPane().add(jButton2, java.awt.BorderLayout.SOUTH); // Add the button to the bottom of the frame
    }
    
    private void goBack(){
        new frm_ApprovalCenter().setVisible(true);
        view.dispose();
    }

    public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            frm_ApprovalCenterPayroll payrollForm = new frm_ApprovalCenterPayroll();
            PayrollController controller = new PayrollController(payrollForm);
            // Do not call generatePayslip here; it should be triggered by an action event.
            payrollForm.setVisible(true);
        }
    });
}
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author User
 */
import frames.frm_ApprovalCenterPayroll;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class PayrollGenerationController extends JFrame {
    public JTextField employeeIdField;
    public JButton generatePayslipButton;
    public frm_ApprovalCenterPayroll view;

    public PayrollGenerationController() {
        new frm_ApprovalCenterPayroll().setVisible(true);
             this.dispose();

        generatePayslipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGeneratePayslip();
            }
        });

        setTitle("Payroll Generation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public void handleGeneratePayslip() {
        int employeeId = Integer.parseInt(employeeIdField.getText());

        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM Employee WHERE employeeId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, employeeId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
               
                double salary = resultSet.getDouble("employeeSalary");
                double deductions = calculateDeductions(employeeId);
                double netPay = salary - deductions;

                // Save payslip to database
                String insertSql = "INSERT INTO Payslip (employeeId, grossPay, netPay) VALUES (?, ?, ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertSql);
                insertStatement.setInt(1, employeeId);
                insertStatement.setDouble(2, salary);
                insertStatement.setDouble(3, netPay);
                insertStatement.executeUpdate();

                JOptionPane.showMessageDialog(this, "Payslip generated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        
        JOptionPane.showMessageDialog(view, "Payslip generated successfully.", "Payslip Generated", JOptionPane.INFORMATION_MESSAGE);
    }

    public double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    public void generateMonthlyReport() {
       
        String report = "Monthly Payroll Report\n======================\n";
        report += "Total Earnings: " + formatCurrency(calculateEarnings());
        report += "\nTotal Benefits: " + formatCurrency(calculateBenefits());
        report += "\nTotal Deductions: " + formatCurrency(calculateDeductions());
        report += "\nTake Home Pay: " + formatCurrency((calculateEarnings() + calculateBenefits()) - calculateDeductions());

        JOptionPane.showMessageDialog(view, report, "Monthly Payroll Report", JOptionPane.INFORMATION_MESSAGE);
    }

    public double calculateEarnings() {
        Double monthlyRate = parseDouble(view.getjFormattedTextField11().getText());
        double dailyRate = parseDouble(view.getjFormattedTextField12().getText());
        int daysWorked = Integer.parseInt(view.getjFormattedTextField13().getText());
        double overtime = parseDouble(view.getjFormattedTextField15().getText());

        return (monthlyRate + (dailyRate * daysWorked) + overtime);
    }

    public double calculateBenefits() {
        double riceAllowance = parseDouble(view.getjFormattedTextField28().getText());
        double phoneAllowance = parseDouble(view.getjFormattedTextField27().getText());
        double clothingAllowance = parseDouble(view.getjFormattedTextField19().getText());

        return (riceAllowance + phoneAllowance + clothingAllowance);
    }

    public double calculateDeductions() {
        double sss = parseDouble(view.getjFormattedTextField21().getText());
        double philHealth = parseDouble(view.getjFormattedTextField22().getText());
        double pagIbig = parseDouble(view.getjFormattedTextField23().getText());
        double tax = parseDouble(view.getjFormattedTextField24().getText());

        return (sss + philHealth + pagIbig + tax);
    }

    public double calculateTakeHomePay(double earnings, double benefits, double deductions) {
        double takeHomePay = earnings + benefits - deductions;
                return takeHomePay;
    }

    public String formatCurrency(double amount) {
        return String.format("₱%,.2f", amount);
    }

    public static void main(String[] args) {
        new PayrollGenerationController().setVisible(true);
    }

    public double calculateDeductions(int employeeId) {
    try (Connection connection = DatabaseConnection.getConnection()) {
        String sql = "SELECT * FROM Deductions WHERE employeeId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, employeeId);

        ResultSet resultSet = statement.executeQuery();
        double totalDeductions = 0.0;
        if (resultSet.next()) {
            double sss = resultSet.getDouble("sss");
            double philHealth = resultSet.getDouble("philHealth");
            double pagIbig = resultSet.getDouble("pagIbig");
            double tax = resultSet.getDouble("tax");
            totalDeductions = sss + philHealth + pagIbig + tax;
        }
        return totalDeductions;
    } catch (SQLException e) {
        e.printStackTrace();
        return 0.0;
    }
}

}



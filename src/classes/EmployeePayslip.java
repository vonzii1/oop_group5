package classes;

import frames.frm_EmployeeDashboard;
import frames.frm_EmployeePayslip;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRResultSetDataSource;

import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmployeePayslip {

    public frm_EmployeePayslip payslipView;
    private Date startDate;
    private Date endDate;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

    public EmployeePayslip(frm_EmployeePayslip payslipView, Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.payslipView = payslipView;
        setupListeners();
    }

    private void setupListeners() {
        payslipView.jButton1.addActionListener(evt -> goBack());
    }

    public void goBack() {
        new frm_EmployeeDashboard().setVisible(true);
        payslipView.dispose();
    }

    public void generatePayslip() {
        try {
            String payslipNumber = payslipView.jFormattedTextField3.getText().trim();
            String employeeId = payslipView.jFormattedTextField6.getText().trim();
            String name = payslipView.jFormattedTextField7.getText().trim();
            String formattedStartDate = sdf.format(startDate);
            String formattedEndDate = sdf.format(endDate);
            String department = payslipView.jFormattedTextField10.getText().trim();

            if (payslipNumber.isEmpty() || employeeId.isEmpty() || name.isEmpty() || department.isEmpty()) {
                JOptionPane.showMessageDialog(payslipView, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            generateReport(payslipNumber, employeeId, name, formattedStartDate, formattedEndDate, department);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(payslipView, "Error in generating payslip: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generateReport(String payslipNumber, String employeeId, String name, String startDate, String endDate, String department) throws SQLException, JRException {
        Connection conn = connect();
        if (conn == null) {
            JOptionPane.showMessageDialog(payslipView, "Database connection error.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "SELECT * FROM employee WHERE employeeId = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, employeeId);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            JOptionPane.showMessageDialog(payslipView, "No employee data found for the given ID.", "Error", JOptionPane.ERROR_MESSAGE);
            rs.close();
            pstmt.close();
            conn.close();
            return;
        }

        JRResultSetDataSource dataSource = new JRResultSetDataSource(rs);

        JasperReport jasperReport = JasperCompileManager.compileReport("motorph_payrollreport.jrxml");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("payslipNumber", payslipNumber);
        parameters.put("employeeId", employeeId);
        parameters.put("name", name);
        parameters.put("startDate", startDate);
        parameters.put("endDate", endDate);
        parameters.put("department", department);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, "motorph_payrollreport_" + employeeId + ".pdf");

        rs.close();
        pstmt.close();
        conn.close();

        JOptionPane.showMessageDialog(payslipView, "Report generated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private Connection connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/payrollsystem_db";
            return DriverManager.getConnection(url, "root", "root");
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            return null;
        }
    }

 }

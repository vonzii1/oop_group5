package classes;

import frames.frm_EmployeeDashboard;
import frames.frm_EmployeeLeave;
import java.awt.Button;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * EmployeeLeaveController handles leave requests by employees.
 */
public class EmployeeLeaveController {
    private frm_EmployeeLeave view;
    private Date jDateChooser2;
    private Date jDateChooser1;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/payrollsystem_db";
    private static final String USER = "root";
    private static final String PASS = "root";

    public EmployeeLeaveController(frm_EmployeeLeave view) {
        this.view = view;
        setupListeners();
    }

    public void submitLeaveRequest() {
        String leaveType = view.jTextField7.getText().trim();
        Date startDate = view.jDateChooser2.getDate();
        Date endDate = view.jDateChooser1.getDate();
        String reason = view.jTextField1.getText().trim();

        if (leaveType.isEmpty() || jDateChooser2 == null || jDateChooser1 == null || reason.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill in all fields before confirming.", "Incomplete Information", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String formattedStartDate = sdf.format(startDate);
        String formattedEndDate = sdf.format(endDate);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "INSERT INTO LeaveRequests (leaveType, startDate, endDate, reason) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, leaveType);
                stmt.setString(2, formattedStartDate);
                stmt.setString(3, formattedEndDate);
                stmt.setString(4, reason);
                stmt.executeUpdate();

                // Provide feedback to the user
                JOptionPane.showMessageDialog(view, "Leave requested!");

                // Switch to employee dashboard view
                new frm_EmployeeDashboard().setVisible(true);
                view.dispose();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error processing leave request: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupListeners() {
        view.jButton2.addActionListener(evt -> submitLeaveRequest());
        view.jButton1.addActionListener(evt -> cancelLeaveRequest());
    }

    public void cancelLeaveRequest() {
        int confirmed = JOptionPane.showConfirmDialog(view, "Are you sure you want to cancel the leave request?", null, JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            // Switch to employee dashboard view
            new frm_EmployeeDashboard().setVisible(true);
            view.dispose();
        }
    }
}


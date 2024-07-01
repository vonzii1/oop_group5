package classes;

import frames.frm_EmployeeAttendance;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AttendanceController {
    private frm_EmployeeAttendance employeeAttendance;
    private Connection conn;

    public AttendanceController(frm_EmployeeAttendance view) {
        this.employeeAttendance = view;
        initDBConnection();
        initView();
        initController();
    }

    AttendanceController() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void initDBConnection() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/payrollsystem_db";
            String username = "root";
            String password = "root";
            conn = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to database: " + e.getMessage());
        }
    }

    public void initView() {
        employeeAttendance.setVisible(true);
    }

    public void initController() {
        employeeAttendance.jButton5.addActionListener(e -> confirmAttendance());
        employeeAttendance.jButton3.addActionListener(e -> applyOvertime(
            employeeAttendance.getOvertimeHours(),
            employeeAttendance.getOvertimeStart(),
            employeeAttendance.getOvertimeEnd(),
            employeeAttendance.getReason()));
    }

    public void applyOvertime(String overtimeHours, String overtimeStart, String overtimeEnd, String reason) {
        String sql = "INSERT INTO overtime (employee_id, hours, start_time, end_time, reason, status) VALUES (?, ?, ?, ?, ?, 'Pending')";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, 123); // Placeholder for employee_id
            statement.setString(2, overtimeHours);
            statement.setString(3, overtimeStart);
            statement.setString(4, overtimeEnd);
            statement.setString(5, reason);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(employeeAttendance, "Overtime application successful.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(employeeAttendance, "Error applying for overtime: " + e.getMessage());
        }
    }

    void confirmAttendance() {
        String sql = "UPDATE attendance SET status = 'Confirmed' WHERE employee_id = ? AND date = CURDATE()";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, 123); // Placeholder for employee_id
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(employeeAttendance, "Attendance confirmed successfully.");
            } else {
                JOptionPane.showMessageDialog(employeeAttendance, "No attendance record to update for today.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(employeeAttendance, "Error confirming attendance: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            frm_EmployeeAttendance employeeAttendance = new frm_EmployeeAttendance();
            AttendanceController controller = new AttendanceController(employeeAttendance);
        });
    }

    boolean markAttendance(String employeeId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

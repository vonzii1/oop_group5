/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import frames.frm_EmployeeAttendance;
import java.awt.Button;
import javax.swing.JOptionPane;


/**
 *
 * @author User
 */
public class AttendanceController {
    private frm_EmployeeAttendance employeeAttendance;
    private Attendance model; // Assuming you have an Attendance model
    private Button jButton3;
    private Button jButton5;
    private Button jButton2;
    
    public AttendanceController(frm_EmployeeAttendance view, Attendance model) {
        this.employeeAttendance = view;
        this.model = model;
        initView();
    }
    
    public void initView() {
        // Initialize view settings or load model data into view
        employeeAttendance.setVisible(true);
    }
    
    public void initController() {
       // employeeAttendance.jButton3.addActionListener(e -> applyOvertime(overtimeHours, overtimeStart, overtimeEnd, reason));
        employeeAttendance.jButton5.addActionListener(e -> confirmAttendance());
        // Add more listeners as needed
    }
    
    public void applyOvertime(String overtimeHours, String overtimeStart, String overtimeEnd, String reason) {
        // Handle overtime application logic
        JOptionPane.showMessageDialog(employeeAttendance, "Applying for overtime...");
    }
    
    private void confirmAttendance() {
        // Handle attendance confirmation logic
        JOptionPane.showMessageDialog(employeeAttendance, "Attendance confirmed...");
    }

    private static class Attendance {

        public Attendance() {
        }
    }
    

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            frm_EmployeeAttendance employeeAttendance = new frm_EmployeeAttendance();
            Attendance model = new Attendance(); // Your model
            AttendanceController controller = new AttendanceController(employeeAttendance, model);
            controller.initController(); // Start listening to view events
        });
    }
}


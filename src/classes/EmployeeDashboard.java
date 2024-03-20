/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import frames.frm_EmployeeAttendance;
import frames.frm_EmployeeDashboard;
import frames.frm_EmployeeLeave;
import frames.frm_EmployeePayslip;
import frames.frm_EmployeeProfile;
import frames.frm_RoleLogin;
import java.awt.Button;
import javax.swing.*;

public class EmployeeDashboard {
    private frm_EmployeeDashboard employeeDashboard;
    private Button jButton5;
    private Button jButton6;
    private Button jButton3;
    private Button jButton4;
    private Button jButton1;

    public EmployeeDashboard() {
        this.employeeDashboard = new frm_EmployeeDashboard();
        addListeners();
    }

    private void addListeners() {
        employeeDashboard.jButton5.addActionListener(e -> onAttendance());
        employeeDashboard.jButton4.addActionListener(e -> onPayslip());
        employeeDashboard.jButton3.addActionListener(e -> onProfile());
        employeeDashboard.jButton6.addActionListener(e -> onLeave());
        employeeDashboard.jButton1.addActionListener(e -> onLogout());
    }

    private void onAttendance() {
        new frm_EmployeeAttendance().setVisible(true);
        employeeDashboard.dispose();
    }

    private void onPayslip() {
        new frm_EmployeePayslip().setVisible(true);
        employeeDashboard.dispose();
    }

    private void onProfile() {
        new frm_EmployeeProfile().setVisible(true);
        employeeDashboard.dispose();
    }

    private void onLeave() {
        new frm_EmployeeLeave().setVisible(true);
        employeeDashboard.dispose();
    }

    private void onLogout() {
        JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", null, 1);
        
        new frm_RoleLogin().setVisible(true);
        //employeeDashboard.dispose();
    }

    public void showDashboard() {
        this.employeeDashboard.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmployeeDashboard controller = new EmployeeDashboard();
            controller.showDashboard();
        });
    }

    
}

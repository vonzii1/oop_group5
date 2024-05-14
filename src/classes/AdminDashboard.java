/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import frames.frm_AdminAttendance;
import frames.frm_AdminDashboard;
import frames.frm_AdminLeave;
import frames.frm_AdminPayslip;
import frames.frm_AdminProfile;
import frames.frm_ApprovalCenter;
import frames.frm_EmployeeRecords;
import frames.frm_RoleLogin;
import java.awt.Button;
import javax.swing.*;

public class AdminDashboard {
    private frm_AdminDashboard adminDashboard;
    private Button jButton5;
    private Button jButton6;
    private Button jButton7;
    private Button jButton4;
    private Button jButton1;
    private Button jButton8;
    private Button jButton3;

    public AdminDashboard() {
        this.adminDashboard = new frm_AdminDashboard();
        addListeners();
    }

    private void addListeners() {
        adminDashboard.jButton5.addActionListener(e -> onAttendance());
        adminDashboard.jButton4.addActionListener(e -> onPayslip());
        adminDashboard.jButton7.addActionListener(e -> onProfile());
        adminDashboard.jButton6.addActionListener(e -> onLeave());
        adminDashboard.jButton1.addActionListener(e -> onLogout());
        adminDashboard.jButton8.addActionListener(e -> onApprovalCenter());
        adminDashboard.jButton3.addActionListener(e -> onEmployeeRecord());
    }

    private void onAttendance() {
        new frm_AdminAttendance().setVisible(true);
        adminDashboard.dispose();
    }

    private void onPayslip() {
        new frm_AdminPayslip().setVisible(true);
        adminDashboard.dispose();
    }

    private void onProfile() {
        new frm_AdminProfile().setVisible(true);
        adminDashboard.dispose();
    }

    private void onLeave() {
        new frm_AdminLeave().setVisible(true);
        adminDashboard.dispose();
    }

    private void onLogout() {
        JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", null, 1);
        
        new frm_RoleLogin().setVisible(true);
        adminDashboard.dispose();
    }
    
    private void onApprovalCenter() {
        new frm_ApprovalCenter().setVisible(true);
        adminDashboard.dispose();
    }

    private void onEmployeeRecord() {
        new frm_EmployeeRecords().setVisible(true);
        adminDashboard.dispose();
    }

    public void showDashboard() {
        this.adminDashboard.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminDashboard controller = new AdminDashboard();
            controller.showDashboard();
        });
    }


    
}

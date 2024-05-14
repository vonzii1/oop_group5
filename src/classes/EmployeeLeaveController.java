/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import frames.frm_EmployeeDashboard;
import frames.frm_EmployeeLeave;
import java.awt.Button;
import javax.swing.JOptionPane;
import org.w3c.dom.Text;

/**
 *
 * @author User
 */
public class EmployeeLeaveController {
    public frm_EmployeeLeave view;
    public Text jTextField7;
    public Text jFormattedTextField2;
    public Text jFormattedTextField4;
    public Text jTextField1;
    public Button jButton2;
    public Button jButton1;
    
    public EmployeeLeaveController(frm_EmployeeLeave view) {
        this.view = view;
        setupListeners();
       
    }

    public void submitLeaveRequest() {
        String leaveType = view.jTextField7.getText().trim();
        String startDate = view.jFormattedTextField2.getText().trim(); 
        String endDate = view.jFormattedTextField4.getText().trim(); 
        String reason = view.jTextField1.getText().trim();
        
        if (leaveType.isEmpty() || startDate.isEmpty() || endDate.isEmpty() || reason.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please fill in all fields before confirming.", "Incomplete Information", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        
        // Provide feedback to the user
        JOptionPane.showMessageDialog(view, "Leave requested!");
        
        // Switch to employee dashboard view
        new frm_EmployeeDashboard().setVisible(true);
        view.dispose();
    }
    private void setupListeners() {
                
        view.jButton2.addActionListener(evt -> submitLeaveRequest());
        view.jButton1.addActionListener(evt -> cancelLeaveRequest());
    }

    public void cancelLeaveRequest() {
        int confirmed = JOptionPane.showConfirmDialog(view, "Leave cancelled?", null, JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            // Handle cancellation logic here, if necessary

            // Switch to employee dashboard view
            new frm_EmployeeDashboard().setVisible(true);
            view.dispose();
        }
    }

}


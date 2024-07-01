/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author User
 */
import frames.frm_EmployeeLeave;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import javax.swing.JOptionPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeLeaveControllerTest {

    @Mock
    private frm_EmployeeLeave view;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockStatement;

    @InjectMocks
    private EmployeeLeaveController controller;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Mocking the individual components of the form
        view.jTextField7 = mock(javax.swing.JTextField.class);
        view.jTextField1 = mock(javax.swing.JTextField.class);
        view.jDateChooser2 = mock(com.toedter.calendar.JDateChooser.class);
        view.jDateChooser1 = mock(com.toedter.calendar.JDateChooser.class);

        when(view.jTextField7.getText()).thenReturn("Annual Leave");
        when(view.jTextField1.getText()).thenReturn("Family event");
        when(view.jDateChooser2.getDate()).thenReturn(new Date());  // use current date for simplicity
        when(view.jDateChooser1.getDate()).thenReturn(new Date());

        // Set up the controller with mocked view
        controller = new EmployeeLeaveController(view);
    }

    @Test
    public void testSubmitLeaveRequest() throws SQLException, java.sql.SQLException {
        when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockStatement);
        doNothing().when(mockStatement).setString(anyInt(), anyString());
        doNothing().when(mockStatement).executeUpdate();

        controller.submitLeaveRequest();

        verify(mockStatement, times(1)).executeUpdate(); // Ensure the statement was executed once
        JOptionPane.showMessageDialog(null, "Leave requested!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    @Test
    public void testCancelLeaveRequest() {
        controller.cancelLeaveRequest();
        verify(view, times(1)).dispose();  // Verify that the view was disposed after canceling the request
        JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the leave request?", "Cancel Leave", JOptionPane.YES_NO_OPTION);
    }

    private static class SQLException extends Exception {
    public SQLException() {
    }

    public SQLException(String message) {
        super(message);
    }

    public SQLException(String message, Throwable cause) {
        super(message, cause);
    }

    public SQLException(Throwable cause) {
        super(cause);
    }
}
}

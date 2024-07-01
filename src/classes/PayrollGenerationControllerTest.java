package classes;

import frames.frm_ApprovalCenterPayroll;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import java.sql.*;
import javax.swing.JFormattedTextField;

public class PayrollGenerationControllerTest {
    private PayrollGenerationController controller;
    public frm_ApprovalCenterPayroll mockedView;
    private Connection mockedConnection;
    private PreparedStatement mockedStatement;
    private ResultSet mockedResultSet;

    @Before
    public void setUp() throws Exception {
        mockedView = mock(frm_ApprovalCenterPayroll.class);
        mockedConnection = mock(Connection.class);
        mockedStatement = mock(PreparedStatement.class);
        mockedResultSet = mock(ResultSet.class);

        when(mockedConnection.prepareStatement(any(String.class))).thenReturn(mockedStatement);
        when(mockedStatement.executeQuery()).thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true); 

        controller = new PayrollGenerationController();
        controller.view = mockedView;
        DatabaseConnection.setConnection(mockedConnection); 
    }

    @Test
    public void testCalculateEarnings() {
        when(mockedView.getjFormattedTextField11()).thenReturn(new JFormattedTextField("20000"));
        when(mockedView.getjFormattedTextField12()).thenReturn(new JFormattedTextField("500"));
        when(mockedView.getjFormattedTextField13()).thenReturn(new JFormattedTextField("20"));
        when(mockedView.getjFormattedTextField15()).thenReturn(new JFormattedTextField("1500"));

        double expected = 20000 + (500 * 20) + 1500;
        double result = controller.calculateEarnings();
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testCalculateBenefits() {
        when(mockedView.getjFormattedTextField28()).thenReturn(new JFormattedTextField("1000"));
        when(mockedView.getjFormattedTextField27()).thenReturn(new JFormattedTextField("500"));
        when(mockedView.getjFormattedTextField19()).thenReturn(new JFormattedTextField("300"));

        double expected = 1000 + 500 + 300;
        double result = controller.calculateBenefits();
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testCalculateDeductions() {
        when(mockedView.getjFormattedTextField21()).thenReturn(new JFormattedTextField("500"));
        when(mockedView.getjFormattedTextField22()).thenReturn(new JFormattedTextField("300"));
        when(mockedView.getjFormattedTextField23()).thenReturn(new JFormattedTextField("200"));
        when(mockedView.getjFormattedTextField24()).thenReturn(new JFormattedTextField("1000"));

        double expected = 500 + 300 + 200 + 1000;
        double result = controller.calculateDeductions();
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testCalculateDeductionsWithEmployeeId() throws SQLException {
        int employeeId = 123;
        when(mockedConnection.prepareStatement(any(String.class))).thenReturn(mockedStatement);
        when(mockedStatement.executeQuery()).thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true);
        when(mockedResultSet.getDouble("sss")).thenReturn(500.0);
        when(mockedResultSet.getDouble("philHealth")).thenReturn(300.0);
        when(mockedResultSet.getDouble("pagIbig")).thenReturn(200.0);
        when(mockedResultSet.getDouble("tax")).thenReturn(1000.0);

        double expected = 500.0 + 300.0 + 200.0 + 1000.0;
        double result = controller.calculateDeductions(employeeId);
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testGeneratePayslip() throws SQLException {
        // Mock necessary fields and database interactions
        when(mockedView.getjFormattedTextField14()).thenReturn(new JFormattedTextField());
        when(mockedView.getjFormattedTextField26()).thenReturn(new JFormattedTextField());
        when(mockedView.getjFormattedTextField25()).thenReturn(new JFormattedTextField());
        when(mockedView.getjFormattedTextField20()).thenReturn(new JFormattedTextField());

        when(mockedResultSet.getDouble("employeeSalary")).thenReturn(20000.00);

        // Call the method
        controller.handleGeneratePayslip();

        // Verify database interactions
        verify(mockedStatement, times(1)).executeUpdate();
        verify(mockedResultSet, times(1)).next();

        // You can add assertions to check if the right values are being set in the view
    }

    @Test
    public void testHandleGeneratePayslipNotFound() throws SQLException {
        when(mockedResultSet.next()).thenReturn(false);

        controller.handleGeneratePayslip();

        verify(mockedStatement, never()).executeUpdate();
    }
}

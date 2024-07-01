/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author User
 */
import static org.mockito.Mockito.*;

import frames.frm_EmployeeRecords;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HRManagementTest {

    @Mock
    private frm_EmployeeRecords employeeRecordsFrame;

    @Mock
    private JButton addButton, deleteButton, updateButton, exitButton;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @InjectMocks
    private HRManagement hrManagement;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(employeeRecordsFrame.getAddButton()).thenReturn(addButton);
        when(employeeRecordsFrame.getDeleteButton()).thenReturn(deleteButton);
        when(employeeRecordsFrame.getUpdateButton()).thenReturn(updateButton);
        when(employeeRecordsFrame.getExitButton()).thenReturn(exitButton);

        hrManagement = new HRManagement();
    }

    @Test
    public void testHandleAddEmployee() throws SQLException {
        when(DatabaseConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        doNothing().when(preparedStatement).executeUpdate();

        hrManagement.handleAddEmployee();

        verify(connection).prepareStatement("INSERT INTO Employee (...) VALUES (?, ?, ?, ?, ?)");
        verify(preparedStatement).executeUpdate();
        verify(employeeRecordsFrame).getAddButton();
    }

    @Test
    public void testHandleDeleteEmployee() throws SQLException {
        when(DatabaseConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        doNothing().when(preparedStatement).executeUpdate();

        hrManagement.handleDeleteEmployee();

        verify(connection).prepareStatement("DELETE FROM Employee WHERE ...");
        verify(preparedStatement).executeUpdate();
        verify(employeeRecordsFrame).getDeleteButton();
    }

    @Test
    public void testHandleModifyEmployee() throws SQLException {
        when(DatabaseConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        doNothing().when(preparedStatement).executeUpdate();

        hrManagement.handleModifyEmployee();

        verify(connection).prepareStatement("UPDATE Employee SET ... WHERE ...");
        verify(preparedStatement).executeUpdate();
        verify(employeeRecordsFrame).getUpdateButton();
    }

    @Test
    public void testExitButtonAction() {
        hrManagement = spy(new HRManagement());
        hrManagement.setUpActionListeners();

        doNothing().when(hrManagement).handleAddEmployee();
        doNothing().when(hrManagement).handleDeleteEmployee();
        doNothing().when(hrManagement).handleModifyEmployee();

        exitButton.doClick();

        verify(hrManagement, never()).handleAddEmployee();
        verify(hrManagement, never()).handleDeleteEmployee();
        verify(hrManagement, never()).handleModifyEmployee();
        verify(employeeRecordsFrame).getExitButton();
    }
}


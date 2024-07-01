/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author User
 */

import frames.frm_EmployeeAttendance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;


class AttendanceControllerTest {

    @Mock
    private frm_EmployeeAttendance employeeAttendance;

    @Mock
    private Connection conn;

    @Mock
    private PreparedStatement preparedStatement;

    @InjectMocks
    private AttendanceController attendanceController;

    @BeforeEach
    public void setup() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(conn.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(employeeAttendance.getOvertimeHours()).thenReturn("2");
        when(employeeAttendance.getOvertimeStart()).thenReturn("18:00");
        when(employeeAttendance.getOvertimeEnd()).thenReturn("20:00");
        when(employeeAttendance.getReason()).thenReturn("Needed for project completion");
        attendanceController = new AttendanceController(employeeAttendance);
    }

    @Test
    void testApplyOvertime() throws SQLException {
        attendanceController.applyOvertime("2", "18:00", "20:00", "Needed for project completion");
        verify(preparedStatement).setInt(1, 123); 
        verify(preparedStatement).setString(2, "2");
        verify(preparedStatement).setString(3, "18:00");
        verify(preparedStatement).setString(4, "20:00");
        verify(preparedStatement).setString(5, "Needed for project completion");
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testConfirmAttendance() throws SQLException {
        attendanceController.confirmAttendance();
        verify(preparedStatement).setInt(1, 123); 
        verify(preparedStatement).executeUpdate();
    }
}


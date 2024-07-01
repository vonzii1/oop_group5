package classes;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;

class MonthlySummaryReportTest {

    private Connection mockConn;
    private Statement mockStmt;
    private ResultSet mockRs;

    @BeforeEach
    void setUp() throws SQLException {
        mockConn = mock(Connection.class);
        mockStmt = mock(Statement.class);
        mockRs = mock(ResultSet.class);

        when(mockConn.createStatement()).thenReturn(mockStmt);
        when(mockStmt.executeQuery(anyString())).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(true, false); // Simulate one row of data

        // Setup dummy data
        when(mockRs.getInt("employeeId")).thenReturn(1);
        when(mockRs.getString("employeeName")).thenReturn("John Doe");
        when(mockRs.getString("position")).thenReturn("Engineer");
        when(mockRs.getDouble("dailyRate")).thenReturn(1000.0);
        when(mockRs.getString("employeeSSSID")).thenReturn("123456789");
        when(mockRs.getString("employeePhilhealthID")).thenReturn("987654321");
        when(mockRs.getString("employeePagibigID")).thenReturn("555666777");
        when(mockRs.getString("employeeBIRNumber")).thenReturn("112233445");
    }

    @Test
    void testFetchEmployeeData() throws SQLException {
        MonthlySummaryReport.setConnection(mockConn); 
        List<MonthlySummaryReport> reports = MonthlySummaryReport.fetchEmployeeData();
        Assertions.assertEquals(1, reports.size());

        MonthlySummaryReport report = reports.get(0);
        Assertions.assertEquals(1, report.employeeId);
        Assertions.assertEquals("John Doe", report.employeeName);
        Assertions.assertEquals("Engineer", report.position);
        Assertions.assertEquals(22000.0, report.grossIncome, 0.01);
    }

    @AfterEach
    void tearDown() throws SQLException {
        mockRs.close();
        mockStmt.close();
        mockConn.close();
    }
}

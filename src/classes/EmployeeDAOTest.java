/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author User
 */
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class EmployeeDAOTest {

    private EmployeeDAO employeeDAO;

    @BeforeEach
    public void setUp() {
        employeeDAO = new EmployeeDAO();
    }

    @Test
    public void testAddEmployee() {
        employeeDAO.addEmployee("John Doe", "123 Main St", "123456789", "Manager", "1990-01-01", "Active", 50000.0, 10, 50.0, "123-456-789", "PH123456", "BIR123", "PAGIBIG-789");

        List<Employee> employees = employeeDAO.getAllEmployees();
        boolean found = employees.stream().anyMatch(emp -> emp.getName().equals("John Doe"));
        assertTrue(found, "Failed to add employee John Doe");
    }

    @Test
    public void testModifyEmployee() {
        int employeeId = 1; // Assuming there's an employee with ID 1 in your database
        employeeDAO.modifyEmployee(employeeId, "Jane Smith", "456 Elm St", "987654321", "Senior Developer", "1985-05-15", 70000.0);

        List<Employee> employees = employeeDAO.getAllEmployees();
        boolean found = employees.stream().anyMatch(emp -> emp.getId() == employeeId && emp.getName().equals("Jane Smith"));
        assertTrue(found, "Failed to modify employee ID " + employeeId);
    }

    @Test
    public void testDeleteEmployee() {
        // Add a temporary employee to delete
        employeeDAO.addEmployee("Temporary Employee", "789 Oak Ave", "555123456", "Temporary", "2000-12-31", "Active", 30000.0, 5, 40.0, "987-654-321", "PH654321", "BIR456", "PAGIBIG-123");

        List<Employee> employeesBeforeDelete = employeeDAO.getAllEmployees();
        int employeeIdToDelete = employeesBeforeDelete.get(employeesBeforeDelete.size() - 1).getId(); 
        employeeDAO.deleteEmployee(employeeIdToDelete);

        List<Employee> employeesAfterDelete = employeeDAO.getAllEmployees();
        boolean found = employeesAfterDelete.stream().anyMatch(emp -> emp.getId() == employeeIdToDelete);
        assertFalse(found, "Failed to delete employee ID " + employeeIdToDelete);
    }
}

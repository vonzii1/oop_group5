/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author User
 */
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.sql.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleLoginIntegrationTest {

    private static Connection conn;

    @BeforeClass
    public static void setUpClass() throws Exception {
        conn = DriverManager.getConnection("jdbc:h2:mem:payrollsystem_db;DB_CLOSE_DELAY=-1");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE users (username VARCHAR(255), password VARCHAR(255), role VARCHAR(255))");
        stmt.execute("INSERT INTO users (username, password, role) VALUES ('admin', 'adminpass', 'admin'), ('user', 'userpass', 'employee')");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        conn.close();
    }

    @Test
    public void testAdminLogin() {
        RoleLogin roleLogin = new RoleLogin();
        roleLogin.authenticateAndNavigate("admin", "adminpass");
      
    }

    @Test
    public void testEmployeeLogin() {
        RoleLogin roleLogin = new RoleLogin();
        roleLogin.authenticateAndNavigate("user", "userpass");
    }

    @Test
    public void testInvalidLogin() {
        RoleLogin roleLogin = new RoleLogin();
        roleLogin.authenticateAndNavigate("wronguser", "wrongpass");
    }
}

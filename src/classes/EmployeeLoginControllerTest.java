package classes;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class EmployeeLoginControllerTest {

    @Test
    public void testValidEmployeeLogin() {
        EmployeeLoginController loginController = new EmployeeLoginController();
        boolean result = loginController.handleLogin("validUsername", "validPassword");
        assertTrue(result, "User should be logged in successfully.");
    }
}

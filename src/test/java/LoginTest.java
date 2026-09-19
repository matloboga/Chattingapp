import com.mycompany.chattingapp.Login;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    
    Login validUser = new Login("Kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kaygee", "Letsoalo");

    @Test public void testUserNameCorrectlyFormatted(){
        assertTrue(validUser.checkUserName());
    }
    @Test public void testUserNameIncorrectlyFormatted(){
        Login l = new Login("Kaygee!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kaygee", "Letsoalo");
        assertFalse(l.checkUserName());
    }
    @Test public void testPasswordMeetsComplexity(){
        assertTrue(validUser.checkPasswordComplexity());
    }
    @Test public void testPasswordDoesNotMeetComplexity(){
        Login l = new Login("Kyl_1", "password", "+27838968976", "Kaygee", "Letsoalo");
        assertFalse(l.checkPasswordComplexity());
    }
    @Test public void testCellNumberCorrectlyFormatted(){
        assertTrue(validUser.checkCellPhoneNumber());
    }
    @Test public void testCellNumberIncorrectlyFormatted(){
        Login l = new Login("Kyl_1", "Ch&&sec@ke99!", "08966553", "Kaygee", "Letsoalo");
        assertFalse(l.checkCellPhoneNumber());
    }
    @Test public void testLoginSuccessful(){
        assertTrue(validUser.loginUser("Kyl_1", "Ch&&sec@ke99!"));
        assertEquals("Welcome Kaygee Letsoalo it is great to see you again.", validUser.returnLoginStatus("Kyl_1", "Ch&&sec@ke99!"));
    }
    @Test public void testLoginFailed(){
        assertFalse(validUser.loginUser("Kyl_1", "wrongPassword"));
        assertEquals("Username or password incorrect, please try again.", validUser.returnLoginStatus("Kyl_1", "wrongPassword"));
    }
}
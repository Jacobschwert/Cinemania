//Jacob Schwertfeger

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

public class AccountTest {
    
    Account test1 = new Account("test1U", "test1E", "test1D", "test1P");

    @Test
    public void testCreateAccount(){
        Account test3 = new Account("test3U", "test3E", "test3D", "test3P");
        assertEquals(test3.getUName(), "test3U");
        assertEquals(test3.getEmail(), "test3E");
        assertEquals(test3.getDescription(), "test3D");
        assertEquals(test3.getPassword(), "test3P");
    }

    @Test
    public void testEditAccount() {
        Account test2 = new Account("test2U", "test2E", "test2D", "test2P");
        test1.editAccount(test2.getUName(), test1.getEmail(), test1.getDescription(), test1.getPassword(), false);
        assertEquals(test1.getUName(), test2.getUName());
    }

    @Test
    public void testDeleteAccount() { 
        Account test4 = new Account("test4U", "test4E", "test4D", "test4P");
        test4.editAccount(test4.getUName(), test4.getEmail(), test4.getDescription(), test4.getPassword(), true); //Adding a true value in the editAccount() will cause it to call deleteAccount()
        assertEquals(test1.getAccountNumber(), 0); //When an account is deleted its account number is set to 0
    }

    @Test
    public void testRecoverAccount() {
        Account test3 = new Account("test3U", "cinemaniaRecover@gmail.com", "test3D", "test3P");
        test3.recoverAccount();
        assertNotEquals("test3P", test3.getPassword());
    }
}

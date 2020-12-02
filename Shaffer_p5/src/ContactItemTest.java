import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class ContactItemTest {
    @Test
    public void creationFailsWithAllBlankValues() {
        assertThrows(IllegalArgumentException.class, ()-> {
            ContactItem contact = new ContactItem("", "","","");
        });
    }

    @Test
    public void creationSucceedsWithBlankEmail() {
        ContactItem contact = new ContactItem("John", "Doe","123-456-7890","");
        assertEquals(null, contact.getEmailAddress());
    }

    @Test
    public void creationSucceedsWithBlankFirstName() {
        ContactItem contact = new ContactItem("", "Doe","123-456-7890","johndoe@gmail.com");
        assertEquals("", contact.getFirstName());
    }

    @Test
    public void creationSucceedsWithBlankLastName() {
        ContactItem contact = new ContactItem("John", "","123-456-7890","johndoe@gmail.com");
        assertEquals("", contact.getFirstName());
    }

    @Test
    public void creationSucceedsWithBlankPhone() {
        ContactItem contact = new ContactItem("John", "Doe","","johndoe@gmail.com");
        assertEquals(null, contact.getPhoneNumber());
    }

    @Test
    public void creationSucceedsWithNonBlankValues() {
        ContactItem contact = new ContactItem("John", "Doe","123-456-7890","johndoe@gmail.com");
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("123-456-7890", contact.getPhoneNumber());
        assertEquals("johndoe@gmail.com", contact.getEmailAddress());
    }

    @Test
    public void editingFailsWithAllBlankValues() {
        assertThrows(IllegalArgumentException.class, ()-> {ContactItem contact = new ContactItem("", "","","");
            contact.setFirstName("Megan");
        });
    }

    @Test
    public void editingSucceedsWithBlankEmail() {
        ContactItem contact = new ContactItem("John", "Doe","123-456-7890","");
        contact.setFirstName("Megan");
        assertEquals("Megan", contact.getFirstName());
    }

    @Test
    public void editingSucceedsWithBlankFirstName() {
        ContactItem contact = new ContactItem("", "Doe","123-456-7890","johndoe@gmail.com");
        contact.setLastName("Megan");
        assertEquals("Megan", contact.getLastName());
    }

    @Test
    public void editingSucceedsWithBlankLastName() {
        ContactItem contact = new ContactItem("John", "","123-456-7890","johndoe@gmail.com");
        contact.setLastName("Megan");
        assertEquals("Megan", contact.getLastName());
    }

    @Test
    public void editingSucceedsWithBlankPhone() {
        ContactItem contact = new ContactItem("John", "Doe","","johndoe@gmail.com");
        contact.setLastName("Megan");
        assertEquals("Megan", contact.getLastName());
    }

    @Test
    public void editingSucceedsWithNonBlankValues() {
        ContactItem contact = new ContactItem("John", "Doe","123-456-7890","johndoe@gmail.com");
        contact.setLastName("Megan");
        assertEquals("Megan", contact.getFirstName());
    }

    @Test
    public void testToString() {
        ContactItem contact = new ContactItem("John", "Doe","123-456-7890","johndoe@gmail.com");
        assertEquals("First Name: John\n Last Name: Doe\n Phone number (xxx-xxx-xxxx): 123-456-7890\n " +
                "Email address (x@y.z): johndoe@gmail.com", contact.toString());
    }
}

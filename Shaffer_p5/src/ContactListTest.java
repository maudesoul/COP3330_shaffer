import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactListTest {

    @Test
    public void addingItemsIncreasesSize() {
        Assertions.assertDoesNotThrow(()->{
            ContactList list = new ContactList();
            assertEquals(list.size(), 0);
            list.addContact(new ContactItem("John", "Doe", "123-456-7890", "johndoe@example.org"));
            assertEquals(list.size(), 1);
        });
    }

    @Test
    public void editingItemsFailsWithAllBlankValues() {
        assertThrows(IllegalArgumentException.class, () -> {
            ContactList list = new ContactList();
            ContactItem contact = new ContactItem("John", "Doe","123-456-7890","johndoe@gmail.com");
            ContactItem newContact = new ContactItem("", "", "", "");
            list.addContact(contact);
            list.getContact(0);
            list.addContact(newContact);
        });
    }

    @Test
    public void editingItemsFailsWithInvalidIndex() {
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("John", "Doe","123-456-7890","johndoe@gmail.com");
        list.addContact(contact);
        try{
            list.getContact("Her","Name","987-654-3210","noname@gmail.com");
            assertTrue(false);
        } catch(ArrayIndexOutOfBoundsException e) {
            assertTrue(true);
        }
    }

    @Test
    public void editingSucceedsWithBlankFirstName() {
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("John", "Doe","123-456-7890","johndoe@gmail.com");
        list.addContact(contact);
        list.getContact("", "Greg", "123-123-1234","greg@gmail.com");
        assertEquals("Greg", contact.getLastName());
    }

    @Test
    public void editingSucceedsWithBlankLastName() {
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("John", "Doe","123-456-7890","johndoe@gmail.com");
        list.addContact(contact);
        list.getContact("Frank", "", "123-123-1234","frank@gmail.com");
        assertEquals("Frank", contact.getFirstName());
    }

    @Test
    public void editingSucceedsWithBlankPhone() {
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("John", "Doe","123-456-7890","johndoe@gmail.com");
        list.addContact(contact);
        list.getContact("Frank", "Synatra", "","frankie@gmail.com");
        assertEquals("Synatra", contact.getLastName());
    }

    @Test
    public void editingSucceedsWithNonBlankValues() {
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("John", "Doe","123-456-7890","johndoe@gmail.com");
        list.addContact(contact);
        list.getContact("Frank", "Synatra", "123-123-1234","frankie@gmail.com");
        assertEquals("Frank", contact.getFirstName());
        assertEquals("Synatra", contact.getLastName());
        assertEquals("123-123-1234", contact.getPhoneNumber());
        assertEquals("frankie@gmail.com", contact.getEmailAddress());
    }

    @Test
    public void newListIsEmpty() {
        ContactList list = new ContactList();
        assertEquals(0, list.size());
    }

    @Test
    public void removingItemsDecreasesSize() {
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("John", "Doe","123-456-7890","johndoe@hotmail.com");
        list.addContact(contact);
        list.removeContact(0);
        assertEquals(0, list.size());
    }

    @Test
    public void removingItemsFailsWithInvalidIndex() {
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("John", "Doe","123-456-7890","johndoe@gmail.com");
        list.addContact(contact);
        try{
            list.removeContact(1);
            fail();
        } catch(ArrayIndexOutOfBoundsException e) { }
    }

    @Test
    public void savedContactListCanBeLoaded() {
        ContactList list = new ContactList();
        ContactItem contact = new ContactItem("Ryou", "Amamiya","123-456-7890","pdg@gmail.com");
        list.addContact(contact);
        list.saveList("text.txt");
        ContactList list2 = new ContactList();
        list2.loadList("text.txt");
        assertEquals(contact, list.getContact(0));
    }
}
